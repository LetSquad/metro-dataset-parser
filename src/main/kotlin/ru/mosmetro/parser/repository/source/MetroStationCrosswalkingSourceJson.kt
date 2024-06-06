package ru.mosmetro.parser.repository.source

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.dto.MetroStationCrosswalkingSourceDTO

@Repository
class MetroStationCrosswalkingSourceJson(
    private val objectMapper: ObjectMapper
) : MetroStationCrosswalkingSourceRepository {

    override fun readMetroStationCrosswalkings(): List<MetroStationCrosswalkingSourceDTO> {
        val metroStationCrosswalkingSource = ClassPathResource(METRO_STATION_CROSSWALKING_SOURCE)
        return objectMapper.readValue(metroStationCrosswalkingSource.inputStream)
    }

    companion object {
        private const val METRO_STATION_CROSSWALKING_SOURCE = "dataset/metro_station_crosswalking.json"
    }
}
