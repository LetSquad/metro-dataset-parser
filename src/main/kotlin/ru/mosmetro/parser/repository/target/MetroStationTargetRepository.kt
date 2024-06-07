package ru.mosmetro.parser.repository.target

import ru.mosmetro.parser.model.entity.MetroStationEntity

interface MetroStationTargetRepository {

    fun saveMetroStation(s: MetroStationEntity)
}
