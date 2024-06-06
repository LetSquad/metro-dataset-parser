package ru.mosmetro.parser.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MetroStationTransferSourceDTO(

    @JsonProperty("id_st1")
    val idSt1: String,

    @JsonProperty("id_st2")
    val idSt2: String,

    val time: String
)
