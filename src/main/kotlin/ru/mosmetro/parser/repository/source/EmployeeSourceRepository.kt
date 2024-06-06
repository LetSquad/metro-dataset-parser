package ru.mosmetro.parser.repository.source

import ru.mosmetro.parser.model.dto.EmployeeSourceDTO

interface EmployeeSourceRepository {

    fun readEmployees(): List<EmployeeSourceDTO>
}
