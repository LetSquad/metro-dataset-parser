package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.entity.EmployeeEntity

@Repository
class EmployeeTargetJdbc(private val jdbcTemplate: JdbcTemplate) : EmployeeTargetRepository {

    override fun saveEmployee(employee: EmployeeEntity) {
        jdbcTemplate.update(
                "INSERT INTO employee" +
                        "(id, first_name, last_name, middle_name, sex, work_start, work_finish, shift_type, work_phone, " +
                        "personal_phone, employee_number, light_duties, rank_code, user_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            employee.id, employee.firstName, employee.lastName, employee.middleName, employee.sex.name, employee.workStart,
            employee.workFinish, employee.shiftType, employee.workPhone, employee.personalPhone, employee.employeeNumber,
            employee.lightDuties, employee.rankCode.name, employee.userId
        )
    }
}
