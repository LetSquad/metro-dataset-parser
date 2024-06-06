package ru.mosmetro.parser.mapper

import org.postgresql.util.PGInterval
import org.springframework.stereotype.Component
import ru.mosmetro.parser.model.dto.MetroStationCrosswalkingSourceDTO
import ru.mosmetro.parser.model.dto.MetroStationTransferSourceDTO
import ru.mosmetro.parser.model.entity.MetroStationTransferEntity

@Component
class MetroStationTransferMapper {

    fun transferSourceDtoToEntity(transfer: MetroStationTransferSourceDTO) = MetroStationTransferEntity(
        startStationId = transfer.idSt1.toLong(),
        finishStationId = transfer.idSt2.toLong(),
        duration = parseDuration(transfer.time),
        isCrosswalking = false
    )

    fun crosswalkingSourceDtoToEntity(transfer: MetroStationCrosswalkingSourceDTO) = MetroStationTransferEntity(
        startStationId = transfer.id1.toLong(),
        finishStationId = transfer.id2.toLong(),
        duration = parseDuration(transfer.time),
        isCrosswalking = true
    )

    private fun parseDuration(time: String): PGInterval {
        val minutesAndSeconds: List<String> = time.split(',')
        val minutes: Int = minutesAndSeconds[0].toInt()
        val seconds: Double = if (minutesAndSeconds.size == 1) 0.0 else minutesAndSeconds[1].toDouble() * 10
        return PGInterval(0, 0, 0, 0, minutes, seconds)
    }
}
