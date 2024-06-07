package ru.mosmetro.parser.repository.source

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.dto.PassengerOrderSourceDTO

@Repository
class PassengerOrderSourceJson(
    private val objectMapper: ObjectMapper
) : PassengerOrderSourceRepository {

    override fun readPassengerOrders(): List<PassengerOrderSourceDTO> {
        val passengerOrderSource = ClassPathResource(PASSENGER_ORDER_SOURCE)
        return objectMapper.readValue(passengerOrderSource.inputStream)
    }

    companion object {
        private const val PASSENGER_ORDER_SOURCE = "dataset/passenger_order.json"
    }
}
