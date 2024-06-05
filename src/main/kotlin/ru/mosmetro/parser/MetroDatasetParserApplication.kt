package ru.mosmetro.parser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MetroDatasetParserApplication

fun main(args: Array<String>) {
    runApplication<MetroDatasetParserApplication>(*args)
}
