package ru.mosmetro.parser.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mosmetro.parser.mapper.EmployeeMapper
import ru.mosmetro.parser.mapper.MetroStationMapper
import ru.mosmetro.parser.mapper.MetroStationTransferMapper
import ru.mosmetro.parser.model.dto.MetroStationSourceDTO
import ru.mosmetro.parser.repository.source.EmployeeSourceRepository
import ru.mosmetro.parser.repository.source.MetroStationCrosswalkingSourceRepository
import ru.mosmetro.parser.repository.source.MetroStationSourceRepository
import ru.mosmetro.parser.repository.source.MetroStationTransferSourceRepository
import ru.mosmetro.parser.repository.target.*

@Service
class MetroDatasetService(
    private val metroStationMapper: MetroStationMapper,
    private val metroStatTransferMapper: MetroStationTransferMapper,
    private val employeeMapper: EmployeeMapper,

    private val metroStationSourceRepository: MetroStationSourceRepository,
    private val metroStationTransferSourceRepository: MetroStationTransferSourceRepository,
    private val metroStationCrosswalkingSourceRepository: MetroStationCrosswalkingSourceRepository,
    private val employeeSourceRepository: EmployeeSourceRepository,

    private val metroLineTargetRepository: MetroLineTargetRepository,
    private val metroStationTargetRepository: MetroStationTargetRepository,
    private val metroStationTransferTargetRepository: MetroStationTransferTargetRepository,
    private val metroUserTargetRepository: MetroUserTargetRepository,
    private val employeeTargetRepository: EmployeeTargetRepository
) {

    @Transactional
    fun parseDataset() {
        val metroStations: List<MetroStationSourceDTO> = metroStationSourceRepository.readMetroStations()

        metroStations.map { metroStationMapper.sourceDtoToLineEntity(it) }
            .toSet()
            .forEach { metroLineTargetRepository.saveMetroLine(it) }

        metroStations.map { metroStationMapper.sourceDtoToEntity(it) }
            .toSet()
            .forEach { metroStationTargetRepository.saveMetroStation(it) }

        metroStationTransferSourceRepository.readMetroStationTransfers()
            .map { metroStatTransferMapper.transferSourceDtoToEntity(it) }
            .toSet()
            .forEach { metroStationTransferTargetRepository.saveMetroStationTrnasfer(it) }

        metroStationCrosswalkingSourceRepository.readMetroStationCrosswalkings()
            .map { metroStatTransferMapper.crosswalkingSourceDtoToEntity(it) }
            .toSet()
            .forEach { metroStationTransferTargetRepository.saveMetroStationTrnasfer(it) }

        employeeSourceRepository.readEmployees()
            .map { employeeMapper.sourceDtoToEntity(it) }
            .toSet()
            .forEach { employee ->
                metroUserTargetRepository.saveUser(employee.userId)
                employeeTargetRepository.saveEmployee(employee)
            }
    }
}
