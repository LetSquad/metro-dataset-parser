package ru.mosmetro.parser.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class PassengerOrderCancelSourceDTO(

    @JsonProperty("ID_BID")
    val idBid: String,

    @JsonProperty("DATE_TIME")
    @JsonFormat(pattern = "dd.MM.uuuu H[H]:mm")
    val dateTime: LocalDateTime
)
