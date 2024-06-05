package ru.mosmetro.parser.model.entity

import ru.mosmetro.parser.model.enums.RankCode
import ru.mosmetro.parser.model.enums.SexEnum
import java.sql.Time

data class EmployeeEntity(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val sex: SexEnum,
    val workStart: Time,
    val workFinish: Time,
    val shiftType: String,
    val workPhone: String?,
    val personalPhone: String?,
    val employeeNumber: Long,
    val lightDuties: Boolean,

    val rankCode: RankCode,
    val userId: Long
)
