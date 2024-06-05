package ru.mosmetro.parser.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mosmetro.parser.repository.source.EmployeeSourceRepository
import ru.mosmetro.parser.repository.target.EmployeeTargetRepository
import ru.mosmetro.parser.repository.target.MetroUserTargetRepository

@Service
class MetroDatasetService(
    private val employeeSourceRepository: EmployeeSourceRepository,
    private val metroUserTargetRepository: MetroUserTargetRepository,
    private val employeeTargetRepository: EmployeeTargetRepository
) {

    @Transactional
    fun parseDataset() {
        for (employee in employeeSourceRepository.readEmployees()) {
            metroUserTargetRepository.saveUser(employee.userId)
            employeeTargetRepository.saveEmployee(employee)
        }
    }
}
