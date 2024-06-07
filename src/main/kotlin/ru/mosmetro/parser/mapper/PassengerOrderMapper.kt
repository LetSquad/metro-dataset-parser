package ru.mosmetro.parser.mapper

import org.postgresql.util.PGInterval
import org.postgresql.util.PGobject
import org.springframework.stereotype.Component
import ru.mosmetro.parser.model.dto.PassengerOrderSourceDTO
import ru.mosmetro.parser.model.entity.PassengerEntity
import ru.mosmetro.parser.model.entity.PassengerOrderEntity
import ru.mosmetro.parser.model.enums.OrderStatus
import ru.mosmetro.parser.model.enums.PassengerCategory
import ru.mosmetro.parser.model.enums.SexEnum
import java.sql.Timestamp

@Component
class PassengerOrderMapper {

    fun sourceDtoToEntity(order: PassengerOrderSourceDTO) = PassengerOrderEntity(
        id = order.id.toLong(),
        duration = PGInterval(order.timeOver),
        transfers = PGobject().apply {
            type = "jsonb"
            value = "[]"
        },
        passengerCount = 1,
        maleEmployeeCount = order.inspSexM.toInt(),
        femaleEmployeeCount = order.inspSexF.toInt(),
        orderTime = Timestamp.valueOf(order.datetime),
        startTime = if (order.time3.toSecondOfDay() == 0) {
            null
        } else {
            Timestamp.valueOf(order.time3.atDate(order.datetime.toLocalDate()))
        },
        finishTime = if (order.time4.toSecondOfDay() == 0) {
            null
        } else {
            Timestamp.valueOf(order.time4.atDate(order.datetime.toLocalDate()))
        },
        orderStatusCode = OrderStatus.fromName(order.status),
        passengerId = order.idPas.toLong(),
        passengerCategory = PassengerCategory.fromName(order.catPas),
        startStationId = order.idSt1,
        finishStationId = order.idSt2
    )

    fun sourceDtoToPassengerEntity(order: PassengerOrderSourceDTO) = PassengerEntity(
        id = order.idPas.toLong(),
        firstName = "Иван ${order.idPas}",
        lastName = "Иванов",
        sex = SexEnum.MALE.name,
        categoryCode = PassengerCategory.fromName(order.catPas)
    )
}
