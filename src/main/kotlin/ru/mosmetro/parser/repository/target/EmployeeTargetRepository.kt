package ru.mosmetro.parser.repository.target

import ru.mosmetro.parser.model.entity.EmployeeEntity

interface EmployeeTargetRepository {

    fun saveEmployee(employee: EmployeeEntity)
}
