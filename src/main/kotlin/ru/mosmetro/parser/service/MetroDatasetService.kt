package ru.mosmetro.parser.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mosmetro.parser.mapper.EmployeeMapper
import ru.mosmetro.parser.mapper.MetroStationMapper
import ru.mosmetro.parser.mapper.MetroStationTransferMapper
import ru.mosmetro.parser.mapper.PassengerOrderMapper
import ru.mosmetro.parser.model.dto.EmployeeSourceDTO
import ru.mosmetro.parser.model.dto.MetroStationSourceDTO
import ru.mosmetro.parser.model.dto.PassengerOrderCancelSourceDTO
import ru.mosmetro.parser.model.dto.PassengerOrderSourceDTO
import ru.mosmetro.parser.model.entity.EmployeeShiftEntity
import ru.mosmetro.parser.repository.source.EmployeeSourceRepository
import ru.mosmetro.parser.repository.source.MetroStationCrosswalkingSourceRepository
import ru.mosmetro.parser.repository.source.MetroStationSourceRepository
import ru.mosmetro.parser.repository.source.MetroStationTransferSourceRepository
import ru.mosmetro.parser.repository.source.PassengerOrderSourceRepository
import ru.mosmetro.parser.repository.target.EmployeeShiftTargetRepository
import ru.mosmetro.parser.repository.target.EmployeeTargetRepository
import ru.mosmetro.parser.repository.target.MetroLineTargetRepository
import ru.mosmetro.parser.repository.target.MetroStationTargetRepository
import ru.mosmetro.parser.repository.target.MetroStationTransferTargetRepository
import ru.mosmetro.parser.repository.target.MetroUserTargetRepository
import ru.mosmetro.parser.repository.target.PassengerOrderTargetRepository
import ru.mosmetro.parser.repository.target.PassengerTargetRepository
import ru.mosmetro.parser.repository.target.UserRefreshTokenTargetRepository
import java.sql.Timestamp
import java.time.LocalDate

@Service
class MetroDatasetService(
    private val metroStationMapper: MetroStationMapper,
    private val metroStatTransferMapper: MetroStationTransferMapper,
    private val employeeMapper: EmployeeMapper,
    private val passengerOrderMapper: PassengerOrderMapper,

    private val metroStationSourceRepository: MetroStationSourceRepository,
    private val metroStationTransferSourceRepository: MetroStationTransferSourceRepository,
    private val metroStationCrosswalkingSourceRepository: MetroStationCrosswalkingSourceRepository,
    private val employeeSourceRepository: EmployeeSourceRepository,
    private val passengerOrderSourceRepository: PassengerOrderSourceRepository,

    private val metroLineTargetRepository: MetroLineTargetRepository,
    private val metroStationTargetRepository: MetroStationTargetRepository,
    private val metroStationTransferTargetRepository: MetroStationTransferTargetRepository,
    private val metroUserTargetRepository: MetroUserTargetRepository,
    private val refreshTokenRepository: UserRefreshTokenTargetRepository,
    private val employeeTargetRepository: EmployeeTargetRepository,
    private val employeeShiftTargetRepository: EmployeeShiftTargetRepository,
    private val passengerTargetRepository: PassengerTargetRepository,
    private val passengerOrderTargetRepository: PassengerOrderTargetRepository
) {

    @Transactional
    fun parseDataset() {
        val metroStations: List<MetroStationSourceDTO> = metroStationSourceRepository.readMetroStations()
        val employees: List<EmployeeSourceDTO> = employeeSourceRepository.readEmployees()

        val ordersCancel: Map<String, PassengerOrderCancelSourceDTO> = passengerOrderSourceRepository
            .readPassengerOrdersCancel()
        val ordersAbsence: Map<String, PassengerOrderCancelSourceDTO> = passengerOrderSourceRepository
            .readPassengerOrdersAbsence()
        val orders: List<PassengerOrderSourceDTO> = passengerOrderSourceRepository.readPassengerOrders()

        metroStations.map { metroStationMapper.sourceDtoToLineEntity(it) }
            .distinctBy { it.id }
            .forEach { metroLineTargetRepository.saveMetroLine(it) }

        metroStations.map { metroStationMapper.sourceDtoToEntity(it) }
            .distinctBy { it.id }
            .forEach { metroStationTargetRepository.saveMetroStation(it) }

        metroStationTransferSourceRepository.readMetroStationTransfers()
            .map { metroStatTransferMapper.transferSourceDtoToEntity(it) }
            .distinct()
            .forEach { metroStationTransferTargetRepository.saveMetroStationTransfer(it) }

        metroStationCrosswalkingSourceRepository.readMetroStationCrosswalkings()
            .map { metroStatTransferMapper.crosswalkingSourceDtoToEntity(it) }
            .distinct()
            .forEach { metroStationTransferTargetRepository.saveMetroStationTransfer(it) }

        employees.map { employeeMapper.sourceDtoToEntity(it) }
            .distinctBy { it.id }
            .forEach { employee ->
                metroUserTargetRepository.saveUser(employee.id, employee.workPhone)
                refreshTokenRepository.saveRefreshToken(employee.workPhone)
                employeeTargetRepository.saveEmployee(employee)
            }

        employees.map { employeeMapper.sourceDtoToShiftEntity(it) }
            .multiplyShifts()
            .forEach { employeeShiftTargetRepository.saveEmployeeShift(it) }

        orders.map { passengerOrderMapper.sourceDtoToPassengerEntity(it) }
            .distinctBy { it.id }
            .forEach { passengerTargetRepository.savePassenger(it) }

        orders.multiplyOrders()
            .map { passengerOrderMapper.sourceDtoToEntity(it, ordersCancel[it.id], ordersAbsence[it.id]) }
            .forEach { passengerOrderTargetRepository.saveOrder(it) }
    }

    private fun List<EmployeeShiftEntity>.multiplyShifts(): List<EmployeeShiftEntity> {
        val currentDate = LocalDate.now()
        val endDate = LocalDate.now().plusMonths(1)

        return flatMap { shift ->
            val orderDate = shift.shiftDate.toLocalDateTime().toLocalDate()

            generateSequence(orderDate) {
                if (it == orderDate) {
                    currentDate
                } else {
                    it.plusDays(1).takeIf { d -> d <= endDate }
                }
            }.map { d ->
                shift.copy(
                    shiftDate = Timestamp.valueOf(d.atStartOfDay())
                )
            }
        }
    }

    private fun List<PassengerOrderSourceDTO>.multiplyOrders(): List<PassengerOrderSourceDTO> {
        val currentDate = LocalDate.now()
        val endDate = LocalDate.now().plusMonths(1)

        return flatMap { order ->
            val orderDate = order.datetime.toLocalDate()

            generateSequence(orderDate) {
                if (it == orderDate) {
                    currentDate
                } else {
                    it.plusDays(1).takeIf { d -> d <= endDate }
                }
            }.map { d ->
                order.copy(
                    id = if (d == orderDate) {
                        order.id
                    } else {
                        (order.id.toLong() * 1000 + d.dayOfYear).toString()
                    },
                    time3 = if (d <= currentDate) {
                        order.time3
                    } else {
                        null
                    },
                    time4 = if (d <= currentDate) {
                        order.time4
                    } else {
                        null
                    },
                    status = if (d <= currentDate) {
                        order.status
                    } else {
                        "В рассмотрении"
                    },
                    datetime = d.atTime(order.datetime.toLocalTime()),
                    tpz =  d.atTime(order.tpz.toLocalTime())
                )
            }
        }
    }
}
