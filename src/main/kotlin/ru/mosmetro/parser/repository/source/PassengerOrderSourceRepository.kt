package ru.mosmetro.parser.repository.source

import ru.mosmetro.parser.model.dto.PassengerOrderCancelSourceDTO
import ru.mosmetro.parser.model.dto.PassengerOrderSourceDTO

interface PassengerOrderSourceRepository {

    fun readPassengerOrders(): List<PassengerOrderSourceDTO>

    fun readPassengerOrdersCancel(): Map<String, PassengerOrderCancelSourceDTO>

    fun readPassengerOrdersAbsence(): Map<String, PassengerOrderCancelSourceDTO>
}
