package ru.mosmetro.parser.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mosmetro.parser.mapper.EmployeeMapper
import ru.mosmetro.parser.mapper.MetroStationMapper
import ru.mosmetro.parser.mapper.MetroStationTransferMapper
import ru.mosmetro.parser.mapper.PassengerOrderMapper
import ru.mosmetro.parser.model.dto.EmployeeSourceDTO
import ru.mosmetro.parser.model.dto.MetroStationSourceDTO
import ru.mosmetro.parser.model.dto.PassengerOrderSourceDTO
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
                metroUserTargetRepository.saveUser(employee.userId)
                refreshTokenRepository.saveRefreshToken(employee.userId.toString())
                employeeTargetRepository.saveEmployee(employee)
            }

        employees.map { employeeMapper.sourceDtoToShiftEntity(it) }
            .forEach { employeeShiftTargetRepository.saveEmployeeShift(it) }

        orders.map { passengerOrderMapper.sourceDtoToPassengerEntity(it) }
            .distinctBy { it.id }
            .forEach { passengerTargetRepository.savePassenger(it) }

        orders.map { passengerOrderMapper.sourceDtoToEntity(it) }
            .forEach { passengerOrderTargetRepository.saveOrder(it) }
    }
}
