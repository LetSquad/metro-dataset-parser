package ru.mosmetro.parser.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class EmployeeSourceDTO(

    @JsonProperty("DATE")
    @JsonFormat(pattern = "dd.MM.uuuu")
    val date: LocalDate,

    @JsonProperty("TIME_WORK")
    val timeWork: String,

    @JsonProperty("ID")
    val id: String,

    @JsonProperty("FIO")
    val fio: String,

    @JsonProperty("UCHASTOK")
    val uchastok: String,

    @JsonProperty("SMENA")
    val smena: String,

    @JsonProperty("RANK")
    val rank: String,

    @JsonProperty("SEX")
    val sex: String
)