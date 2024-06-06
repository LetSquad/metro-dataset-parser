package ru.mosmetro.parser.repository.source

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.dto.EmployeeSourceDTO

@Repository
class EmployeeSourceJson(
    private val objectMapper: ObjectMapper
) : EmployeeSourceRepository {

    override fun readEmployees(): List<EmployeeSourceDTO> {
        val employeeSource = ClassPathResource(EMPLOYEE_SOURCE)
        return objectMapper.readValue(employeeSource.inputStream)
    }

    companion object {
        private const val EMPLOYEE_SOURCE = "dataset/employee.json"
    }
}
