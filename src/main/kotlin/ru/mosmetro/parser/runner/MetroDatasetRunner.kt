package ru.mosmetro.parser.runner

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import ru.mosmetro.parser.getLogger
import ru.mosmetro.parser.service.MetroDatasetService

@Component
class MetroDatasetRunner(
    private val metroDatasetService: MetroDatasetService
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        log.info("Dataset parsing started")

        metroDatasetService.parseDataset()

        log.info("Dataset parsing finished successfully")
    }

    companion object {
        private val log = getLogger<MetroDatasetRunner>()
    }
}
