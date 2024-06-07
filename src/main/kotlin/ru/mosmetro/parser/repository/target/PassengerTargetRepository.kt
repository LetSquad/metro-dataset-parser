package ru.mosmetro.parser.repository.target

import ru.mosmetro.parser.model.entity.PassengerEntity

interface PassengerTargetRepository {

    fun savePassenger(p: PassengerEntity)
}
