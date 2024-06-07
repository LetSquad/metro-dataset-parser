package ru.mosmetro.parser.model.entity

import java.sql.Time
import java.sql.Timestamp

data class EmployeeShiftEntity(
    val shiftDate: Timestamp,
    val workStart: Time,
    val workFinish: Time,
    val employeeId: Long
)
