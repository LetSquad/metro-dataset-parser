package ru.mosmetro.parser.model.entity

import org.postgresql.util.PGInterval

data class MetroStationTransferEntity(
    val startStationId: Long,
    val finishStationId: Long,
    val duration: PGInterval,
    val isCrosswalking: Boolean
)
