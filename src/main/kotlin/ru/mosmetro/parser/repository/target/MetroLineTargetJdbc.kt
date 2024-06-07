package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.entity.MetroLineEntity

@Repository
class MetroLineTargetJdbc(
    private val jdbcTemplate: JdbcTemplate
) : MetroLineTargetRepository {

    override fun saveMetroLine(l: MetroLineEntity) {
        jdbcTemplate.update(
            "INSERT INTO metro_line(id, name, color) VALUES (?, ?, ?)",
            l.id, l.name, l.color
        )
    }
}
