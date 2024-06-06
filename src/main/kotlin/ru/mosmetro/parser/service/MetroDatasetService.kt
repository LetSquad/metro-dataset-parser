package ru.mosmetro.parser.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mosmetro.parser.mapper.EmployeeMapper
import ru.mosmetro.parser.mapper.MetroStationMapper
import ru.mosmetro.parser.model.dto.MetroStationSourceDTO
import ru.mosmetro.parser.repository.source.EmployeeSourceRepository
import ru.mosmetro.parser.repository.source.MetroStationSourceRepository
import ru.mosmetro.parser.repository.target.EmployeeTargetRepository
import ru.mosmetro.parser.repository.target.MetroLineTargetRepository
import ru.mosmetro.parser.repository.target.MetroStationTargetRepository
import ru.mosmetro.parser.repository.target.MetroUserTargetRepository

@Service
class MetroDatasetService(
    private val metroStationMapper: MetroStationMapper,
    private val employeeMapper: EmployeeMapper,

    private val metroStationSourceRepository: MetroStationSourceRepository,
    private val employeeSourceRepository: EmployeeSourceRepository,

    private val metroLineTargetRepository: MetroLineTargetRepository,
    private val metroStationTargetRepository: MetroStationTargetRepository,
    private val metroUserTargetRepository: MetroUserTargetRepository,
    private val employeeTargetRepository: EmployeeTargetRepository
) {

    @Transactional
    fun parseDataset() {
        val metroStations: List<MetroStationSourceDTO> = metroStationSourceRepository.readMetroStations()

        metroStations.map { metroStationMapper.sourceDtoToLineEntity(it) }
            .toSet()
            .forEach { line ->
                metroLineTargetRepository.saveMetroLine(line)
            }

        metroStations.map { metroStationMapper.sourceDtoToEntity(it) }
            .toSet()
            .forEach { station ->
                metroStationTargetRepository.saveMetroStation(station)
            }

        employeeSourceRepository.readEmployees()
            .map { employeeMapper.sourceDtoToEntity(it) }
            .toSet()
            .forEach { employee ->
                metroUserTargetRepository.saveUser(employee.userId)
                employeeTargetRepository.saveEmployee(employee)
            }
    }
}
