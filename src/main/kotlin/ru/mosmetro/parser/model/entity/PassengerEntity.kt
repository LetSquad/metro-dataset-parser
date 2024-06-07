package ru.mosmetro.parser.model.entity

import ru.mosmetro.parser.model.enums.PassengerCategory

data class PassengerEntity(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val sex: String,
    val categoryCode: PassengerCategory
)
