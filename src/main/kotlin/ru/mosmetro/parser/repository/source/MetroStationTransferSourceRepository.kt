package ru.mosmetro.parser.repository.source

import ru.mosmetro.parser.model.dto.MetroStationTransferSourceDTO

interface MetroStationTransferSourceRepository {

    fun readMetroStationTransfers(): List<MetroStationTransferSourceDTO>
}
