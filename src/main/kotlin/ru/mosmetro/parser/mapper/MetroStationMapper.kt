package ru.mosmetro.parser.mapper

import org.springframework.stereotype.Component
import ru.mosmetro.parser.model.dto.MetroStationSourceDTO
import ru.mosmetro.parser.model.entity.MetroLineEntity
import ru.mosmetro.parser.model.entity.MetroStationEntity

@Component
class MetroStationMapper {

    fun sourceDtoToEntity(station: MetroStationSourceDTO) = MetroStationEntity(
        id = station.id.toLong(),
        name = station.nameStation,
        lineId = retrieveLineId(station)
    )

    fun sourceDtoToLineEntity(station: MetroStationSourceDTO) = MetroLineEntity(
        id = retrieveLineId(station),
        name = station.nameLine
    )

    private fun retrieveLineId(station: MetroStationSourceDTO): Long = when (station.nameLine) {
        "4А" -> 44
        "8А" -> 88
        "БКЛ(А)" -> 111
        else -> station.idLine.toLong()
    }
}
