package ru.mosmetro.parser.repository.target

import ru.mosmetro.parser.model.entity.MetroStationTransferEntity

interface MetroStationTransferTargetRepository {

    fun saveMetroStationTrnasfer(transfer: MetroStationTransferEntity)
}
