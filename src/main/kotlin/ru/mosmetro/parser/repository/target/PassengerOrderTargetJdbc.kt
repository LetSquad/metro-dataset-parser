package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.entity.PassengerOrderEntity

@Repository
class PassengerOrderTargetJdbc(
    private val jdbcTemplate: JdbcTemplate
) : PassengerOrderTargetRepository {

    override fun saveOrder(o: PassengerOrderEntity) {
        jdbcTemplate.update(
            "INSERT INTO passenger_order(id, duration, transfers, passenger_count, male_employee_count, " +
                    "female_employee_count, order_time, start_time, finish_time, order_status_code, passenger_id, " +
                    "passenger_category, start_station_id, finish_station_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            o.id, o.duration, o.transfers, o.passengerCount, o.maleEmployeeCount,
            o.femaleEmployeeCount, o.orderTime, o.startTime, o.finishTime, o.orderStatusCode.name,
            o.passengerId, o.passengerCategory.name, o.startStationId, o.finishStationId
        )
    }
}
