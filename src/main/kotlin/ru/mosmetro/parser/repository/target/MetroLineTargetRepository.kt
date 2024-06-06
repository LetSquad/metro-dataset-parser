package ru.mosmetro.parser.repository.target

import ru.mosmetro.parser.model.entity.MetroLineEntity

interface MetroLineTargetRepository {

    fun saveMetroLine(metroLine: MetroLineEntity)
}
