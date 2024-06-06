package ru.mosmetro.parser.mapper

import org.springframework.stereotype.Component
import ru.mosmetro.parser.model.dto.EmployeeSourceDTO
import ru.mosmetro.parser.model.entity.EmployeeEntity
import ru.mosmetro.parser.model.enums.RankCode
import ru.mosmetro.parser.model.enums.SexEnum
import java.sql.Time

@Component
class EmployeeMapper {

    fun sourceDtoToEntity(employee: EmployeeSourceDTO): EmployeeEntity {
        val id: Long = employee.id.toLong()
        val fullName: List<String> = employee.fio.split(' ', '.')
        val workTime: List<String> = employee.timeWork.split('-')

        return EmployeeEntity(
            id = id,
            firstName = fullName[FIRST_NAME_INDEX],
            lastName = fullName[LAST_NAME_INDEX],
            middleName = fullName[MIDDLE_NAME_INDEX],
            sex = when (employee.sex) {
                SEX_MALE -> SexEnum.MALE
                SEX_FEMALE -> SexEnum.FEMALE
                else -> SexEnum.MALE
            },
            workStart = Time.valueOf(workTime[TIME_WORK_START_INDEX] + ":00"),
            workFinish = Time.valueOf(workTime[TIME_WORK_FINISH_INDEX] + ":00"),
            shiftType = employee.smena,
            workPhone = null,
            personalPhone = null,
            employeeNumber = id,
            lightDuties = false,
            rankCode = when (employee.rank) {
                RANK_SECTION_FOREMAN -> RankCode.SECTION_FOREMAN
                RANK_CHIEF_INSPECTOR -> RankCode.CHIEF_INSPECTOR
                RANK_INSPECTOR -> RankCode.INSPECTOR
                else -> RankCode.INSPECTOR
            },
            userId = id
        )
    }

    companion object {
        private const val LAST_NAME_INDEX = 0
        private const val FIRST_NAME_INDEX = 1
        private const val MIDDLE_NAME_INDEX = 2

        private const val TIME_WORK_START_INDEX = 0
        private const val TIME_WORK_FINISH_INDEX = 1

        private const val SEX_MALE = "Мужской"
        private const val SEX_FEMALE = "Женский"

        private const val RANK_SECTION_FOREMAN = "ЦУ"
        private const val RANK_CHIEF_INSPECTOR = "ЦСИ"
        private const val RANK_INSPECTOR = "ЦИ"
    }
}
