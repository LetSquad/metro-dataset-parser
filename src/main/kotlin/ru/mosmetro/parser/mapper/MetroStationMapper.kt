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
        name = station.nameLine,
        color = retrieveLineColor(station.nameLine)
    )

    private fun retrieveLineId(station: MetroStationSourceDTO): Long = when (station.nameLine) {
        "4А" -> 44
        "8А" -> 88
        "БКЛ(А)" -> 111
        else -> station.idLine.toLong()
    }

    private fun retrieveLineColor(name: String): String = when (name) {
        "1" -> "#EF161E"
        "2" -> "#2DBE2C"
        "3" -> "#0078BE"
        "4", "4А" -> "#00BFFF"
        "5" -> "#8D5B2D"
        "6" -> "#ED9121"
        "7" -> "#800080"
        "8", "8А" -> "#FFD702"
        "9" -> "#999999"
        "10" -> "#99CC00"
        "11", "БКЛ", "БКЛ(А)" -> "#82C0C0"
        "12", "Л1" -> "#A1B3D4"
        "14" -> "#FFFFFF"
        "15" -> "#DE64A1"
        "Д1" -> "#F6A600"
        "Д2" -> "#E74280"
        "Д3" -> "#E95B0C"
        "Д4" -> "#40B280"
        else -> throw Exception("Unknown line name = $name")
    }
}
