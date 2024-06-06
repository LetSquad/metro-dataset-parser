package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.entity.MetroStationEntity

@Repository
class MetroStationTargetJdbc(
    private val jdbcTemplate: JdbcTemplate
) : MetroStationTargetRepository {

    override fun saveMetroStation(station: MetroStationEntity) {
        jdbcTemplate.update(
            "INSERT INTO metro_station(id, name, line_id) VALUES (?, ?, ?)",
            station.id, station.name, station.lineId
        )
    }
}
