package ru.mosmetro.parser.model.entity

import org.postgresql.util.PGInterval
import org.postgresql.util.PGobject
import ru.mosmetro.parser.model.enums.OrderStatus
import ru.mosmetro.parser.model.enums.PassengerCategory
import java.sql.Timestamp

data class PassengerOrderEntity(
    val id: Long,
    val duration: PGInterval,
    val transfers: PGobject,
    val passengerCount: Int,
    val maleEmployeeCount: Int,
    val femaleEmployeeCount: Int,
    val orderTime: Timestamp,
    val startTime: Timestamp?,
    val finishTime: Timestamp?,
    val cancelTime: Timestamp?,
    val absenceTime: Timestamp?,

    val orderStatusCode: OrderStatus,
    val passengerId: Long,
    val passengerCategory: PassengerCategory,
    val startStationId: Long,
    val finishStationId: Long,

    val createdAt: Timestamp
)
