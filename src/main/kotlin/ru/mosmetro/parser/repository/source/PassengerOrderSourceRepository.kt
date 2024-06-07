package ru.mosmetro.parser.repository.source

import ru.mosmetro.parser.model.dto.PassengerOrderSourceDTO

interface PassengerOrderSourceRepository {

    fun readPassengerOrders(): List<PassengerOrderSourceDTO>
}
