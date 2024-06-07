package ru.mosmetro.parser.model.enums

enum class SexEnum {

    MALE,
    FEMALE;

    companion object {

        fun fromName(name: String): SexEnum = when (name) {
            SEX_MALE -> MALE
            SEX_FEMALE -> FEMALE
            else -> MALE
        }

        private const val SEX_MALE = "Мужской"
        private const val SEX_FEMALE = "Женский"
    }
}
