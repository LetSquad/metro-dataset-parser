package ru.mosmetro.parser.repository.target

import ru.mosmetro.parser.model.entity.PassengerOrderEntity

interface PassengerOrderTargetRepository {

    fun saveOrder(o: PassengerOrderEntity)
}
