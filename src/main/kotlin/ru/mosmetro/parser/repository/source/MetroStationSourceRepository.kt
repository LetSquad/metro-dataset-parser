package ru.mosmetro.parser.repository.source

import ru.mosmetro.parser.model.dto.MetroStationSourceDTO

interface MetroStationSourceRepository {

    fun readMetroStations(): List<MetroStationSourceDTO>
}
