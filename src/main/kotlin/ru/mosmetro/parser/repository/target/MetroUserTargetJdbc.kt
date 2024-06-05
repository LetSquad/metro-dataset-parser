package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class MetroUserTargetJdbc(private val jdbcTemplate: JdbcTemplate) : MetroUserTargetRepository {

    override fun saveUser(id: Long) {
        jdbcTemplate.update(
            "INSERT INTO metro_user(id, login, password, is_password_temporary) " +
                    "VALUES (?, ?, ?, ?)", id, id, TEMPORARY_PASSWORD, true
        )
    }

    companion object {
        private const val TEMPORARY_PASSWORD = "temp"
    }
}
