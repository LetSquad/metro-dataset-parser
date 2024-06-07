package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mosmetro.parser.model.entity.PassengerEntity

@Repository
class PassengerTargetJdbc(
    private val jdbcTemplate: JdbcTemplate
) : PassengerTargetRepository {

    override fun savePassenger(p: PassengerEntity) {
        jdbcTemplate.update(
            "INSERT INTO passenger(id, first_name, last_name, sex, category_code) VALUES (?, ?, ?, ?, ?)",
            p.id, p.firstName, p.lastName, p.sex, p.categoryCode.name
        )
    }
}
