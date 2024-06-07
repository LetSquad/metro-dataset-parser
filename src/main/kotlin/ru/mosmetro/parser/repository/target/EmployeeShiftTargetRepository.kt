package ru.mosmetro.parser.repository.target

import ru.mosmetro.parser.model.entity.EmployeeShiftEntity

interface EmployeeShiftTargetRepository {

    fun saveEmployeeShift(s: EmployeeShiftEntity)
}
