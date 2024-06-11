package ru.mosmetro.parser.repository.target

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UserRefreshTokenTargetJdbc(private val jdbcTemplate: JdbcTemplate) : UserRefreshTokenTargetRepository {

    override fun saveRefreshToken(login: String) {
        jdbcTemplate.update("INSERT INTO user_refresh_token(user_login) VALUES (?)", login)
    }
}
