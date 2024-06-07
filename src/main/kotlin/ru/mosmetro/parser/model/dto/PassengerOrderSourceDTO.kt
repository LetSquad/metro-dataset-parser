package ru.mosmetro.parser.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.LocalTime

data class PassengerOrderSourceDTO(

    val id: String,

    @JsonProperty("id_pas")
    val idPas: String,

    @JsonFormat(pattern = "dd.MM.uuuu H[H]:mm:ss")
    val datetime: LocalDateTime,

    @JsonFormat(pattern = "HH:mm:ss")
    val time3: LocalTime,

    @JsonFormat(pattern = "HH:mm:ss")
    val time4: LocalTime,

    @JsonProperty("cat_pas")
    val catPas: String,

    val status: String,

    @JsonFormat(pattern = "dd.MM.uuuu H[H]:mm:ss")
    val tpz: LocalDateTime,

    @JsonProperty("INSP_SEX_M")
    val inspSexM: String,

    @JsonProperty("INSP_SEX_F")
    val inspSexF: String,

    @JsonProperty("TIME_OVER")
    val timeOver: String,

    @JsonProperty("id_st1")
    val idSt1: Long,

    @JsonProperty("id_st2")
    val idSt2: Long
)
