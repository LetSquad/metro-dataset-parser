package ru.mosmetro.parser.repository.source

import ru.mosmetro.parser.model.dto.MetroStationCrosswalkingSourceDTO

interface MetroStationCrosswalkingSourceRepository {

    fun readMetroStationCrosswalkings(): List<MetroStationCrosswalkingSourceDTO>
}
