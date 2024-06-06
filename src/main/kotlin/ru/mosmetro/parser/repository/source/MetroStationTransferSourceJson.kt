package ru.mosmetro.parser.repository.source

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.dto.MetroStationTransferSourceDTO

@Repository
class MetroStationTransferSourceJson(
    private val objectMapper: ObjectMapper
) : MetroStationTransferSourceRepository {

    override fun readMetroStationTransfers(): List<MetroStationTransferSourceDTO> {
        val metroStationTransferSource = ClassPathResource(METRO_STATION_TRANSFER_SOURCE)
        return objectMapper.readValue(metroStationTransferSource.inputStream)
    }

    companion object {
        private const val METRO_STATION_TRANSFER_SOURCE = "dataset/metro_station_transfer.json"
    }
}
