package com.etu.ridesharing.data
import java.util.UUID;
object DataCarInfoList {
    val carList = mutableListOf<CarInfoState>(
        CarInfoState(
            mark = "Toyota",
            color =  "Красный",
            number = "А001АА",
        ),
        CarInfoState(
            mark = "Volkswagen",
            color =  "Оранжевый",
            number = "В002ВВ",
        ),
        CarInfoState(
            mark = "Ford",
            color =  "Чёрный",
            number = "Е003ЕЕ",
        ),
        CarInfoState(
            mark = "Hyundai",
            color =  "Серый",
            number = "К004КК",
        ),
        CarInfoState(
            mark = "Honda",
            color =  "Белый",
            number = "М005ММ",
        ),
        CarInfoState(
            mark = "Nissan",
            color =  "Синий",
            number = "Н006НН",
        ),
        CarInfoState(
            mark = "Chevrolet",
            color =  "Фиолетовый",
            number = "О007ОО",
        ),
    )
}