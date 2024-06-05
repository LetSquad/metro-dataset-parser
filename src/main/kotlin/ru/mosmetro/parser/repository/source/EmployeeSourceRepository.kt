package ru.mosmetro.parser.repository.source

import ru.mosmetro.parser.model.entity.EmployeeEntity

interface EmployeeSourceRepository {

    fun readEmployees(): List<EmployeeEntity>
}
