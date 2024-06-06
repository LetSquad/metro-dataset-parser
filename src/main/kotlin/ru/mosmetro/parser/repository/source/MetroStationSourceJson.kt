package ru.mosmetro.parser.repository.source

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.dto.MetroStationSourceDTO

@Repository
class MetroStationSourceJson(
    private val objectMapper: ObjectMapper
) : MetroStationSourceRepository {

    override fun readMetroStations(): List<MetroStationSourceDTO> {
        val metroStationSource = ClassPathResource(METRO_STATION_SOURCE)
        return objectMapper.readValue(metroStationSource.inputStream)
    }

    companion object {
        private const val METRO_STATION_SOURCE = "dataset/metro_station.json"
    }
}
