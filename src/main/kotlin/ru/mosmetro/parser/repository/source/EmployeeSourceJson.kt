package ru.mosmetro.parser.repository.source

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.mapper.EmployeeMapper
import ru.mosmetro.parser.model.entity.EmployeeEntity

@Repository
class EmployeeSourceJson(
    private val objectMapper: ObjectMapper,
    private val employeeMapper: EmployeeMapper
) : EmployeeSourceRepository {

    override fun readEmployees(): List<EmployeeEntity> {
        val employeeSource = ClassPathResource(EMPLOYEE_SOURCE)

        return objectMapper.readValue<List<Map<String, String>>>(employeeSource.inputStream)
            .map { employeeMapper.sourceMapToEntity(it) }
            .distinct()
    }

    companion object {
        private const val EMPLOYEE_SOURCE = "dataset/employee.json"
    }
}
