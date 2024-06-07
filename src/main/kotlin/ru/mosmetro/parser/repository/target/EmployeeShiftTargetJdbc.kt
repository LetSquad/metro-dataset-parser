package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.entity.EmployeeShiftEntity


@Repository
class EmployeeShiftTargetJdbc(
    private val jdbcTemplate: JdbcTemplate
) : EmployeeShiftTargetRepository {

    override fun saveEmployeeShift(s: EmployeeShiftEntity) {
        jdbcTemplate.update(
            "INSERT INTO employee_shift(id, shift_date, work_start, work_finish, employee_id) " +
                    "VALUES (nextval('employee_shift_id_seq'), ?, ?, ?, ?)",
            s.shiftDate, s.workStart, s.workFinish, s.employeeId
        )
    }
}
