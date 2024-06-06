package ru.mosmetro.parser.model.entity

data class MetroStationEntity(
    val id: Long,
    val name: String,
    val lineId: Long
)
