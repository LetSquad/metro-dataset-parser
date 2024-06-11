package ru.mosmetro.parser.repository.target

interface UserRefreshTokenTargetRepository {

    fun saveRefreshToken(login: String)
}
