package ru.mosmetro.parser.model.enums

enum class OrderStatus {

    NOT_CONFIRMED,
    REVIEW,
    ACCEPTED,
    INSPECTOR_WENT,
    INSPECTOR_ARRIVED,
    RIDE,
    COMPLETED,
    IDENTIFICATION,
    WAITING_LIST,
    CANCELED,
    REJECTED,
    PASSENGER_LATE,
    INSPECTOR_LATE;

    companion object {

        fun fromName(name: String): OrderStatus = when (name) {
            "Не подтверждена" -> NOT_CONFIRMED
            "В рассмотрении" -> REVIEW
            "Принята" -> ACCEPTED
            "Инспектор выехал" -> INSPECTOR_WENT
            "Инспектор на месте" -> INSPECTOR_ARRIVED
            "Поездка" -> RIDE
            "Заявка закончена" -> COMPLETED
            "Выявление" -> IDENTIFICATION
            "Лист ожидания" -> WAITING_LIST
            "Отмена", "Отмена заявки по просьбе пассажира", "Отмена заявки по неявке пассажира" -> CANCELED
            "Отказ", "Отказ по регламенту" -> REJECTED
            "Пассажир опаздывает" -> PASSENGER_LATE
            "Инспектор опаздывает" -> INSPECTOR_LATE
            else -> throw Exception("Unknown order status name = $name")
        }
    }
}
