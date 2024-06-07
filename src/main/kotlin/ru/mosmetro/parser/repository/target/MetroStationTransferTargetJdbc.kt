package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.entity.MetroStationTransferEntity

@Repository
class MetroStationTransferTargetJdbc(
    private val jdbcTemplate: JdbcTemplate
) : MetroStationTransferTargetRepository {

    override fun saveMetroStationTransfer(t: MetroStationTransferEntity) {
        jdbcTemplate.update(
            "INSERT INTO metro_station_transfer(start_station_id, finish_station_id, duration, is_crosswalking) " +
                    "VALUES (?, ?, ?, ?)",
            t.startStationId, t.finishStationId, t.duration, t.isCrosswalking
        )
    }
}
