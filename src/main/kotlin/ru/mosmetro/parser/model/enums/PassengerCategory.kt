package ru.mosmetro.parser.model.enums

enum class PassengerCategory {

    IZT,
    IZ,
    IS,
    IK,
    IO,
    DI,
    PL,
    RD,
    RDK,
    OGD,
    OV,
    IU;

    companion object {

        fun fromName(name: String): PassengerCategory = when (name) {
            "ИЗТ" -> IZT
            "ИЗ" -> IZ
            "ИС" -> IS
            "ИК" -> IK
            "ИО" -> IO
            "ДИ" -> DI
            "ПЛ" -> PL
            "РД" -> RD
            "РДК" -> RDK
            "ОГД" -> OGD
            "ОВ" -> OV
            "ИУ" -> IU
            else -> throw Exception("Unknown passenger category name = $name")
        }
    }
}