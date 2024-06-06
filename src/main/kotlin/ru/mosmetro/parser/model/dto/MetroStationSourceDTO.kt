package ru.mosmetro.parser.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MetroStationSourceDTO(

    @JsonProperty("name_station")
    val nameStation: String,

    @JsonProperty("name_line")
    val nameLine: String,

    @JsonProperty("id")
    val id: String,

    @JsonProperty("id_line")
    val idLine: String
)
