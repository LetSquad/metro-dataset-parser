package ru.mosmetro.parser.repository.source

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.dto.PassengerOrderCancelSourceDTO
import ru.mosmetro.parser.model.dto.PassengerOrderSourceDTO

@Repository
class PassengerOrderSourceJson(
    private val objectMapper: ObjectMapper
) : PassengerOrderSourceRepository {

    override fun readPassengerOrders(): List<PassengerOrderSourceDTO> {
        val passengerOrderSource = ClassPathResource(PASSENGER_ORDER_SOURCE)
        return objectMapper.readValue(passengerOrderSource.inputStream)
    }

    override fun readPassengerOrdersCancel(): Map<String, PassengerOrderCancelSourceDTO> {
        val passengerOrderSource = ClassPathResource(PASSENGER_ORDER_CANCEL_SOURCE)
        return objectMapper.readValue<List<PassengerOrderCancelSourceDTO>>(passengerOrderSource.inputStream)
            .associateBy { it.idBid }
    }

    override fun readPassengerOrdersAbsence(): Map<String, PassengerOrderCancelSourceDTO> {
        val passengerOrderSource = ClassPathResource(PASSENGER_ORDER_ABSENCE_SOURCE)
        return objectMapper.readValue<List<PassengerOrderCancelSourceDTO>>(passengerOrderSource.inputStream)
            .associateBy { it.idBid }
    }

    companion object {
        private const val PASSENGER_ORDER_SOURCE = "dataset/passenger_order.json"
        private const val PASSENGER_ORDER_CANCEL_SOURCE = "dataset/passenger_order_cancel.json"
        private const val PASSENGER_ORDER_ABSENCE_SOURCE = "dataset/passenger_order_absence.json"
    }
}
