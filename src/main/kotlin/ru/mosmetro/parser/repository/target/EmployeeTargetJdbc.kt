package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.entity.EmployeeEntity

@Repository
class EmployeeTargetJdbc(private val jdbcTemplate: JdbcTemplate) : EmployeeTargetRepository {

    override fun saveEmployee(e: EmployeeEntity) {
        jdbcTemplate.update(
                "INSERT INTO employee" +
                        "(id, first_name, last_name, middle_name, sex, work_start, work_finish, shift_type, work_phone, " +
                        "personal_phone, employee_number, light_duties, rank_code, user_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            e.id, e.firstName, e.lastName, e.middleName, e.sex.name, e.workStart, e.workFinish, e.shiftType,
            e.workPhone, e.personalPhone, e.employeeNumber, e.lightDuties, e.rankCode.name, e.userId
        )
    }
}
