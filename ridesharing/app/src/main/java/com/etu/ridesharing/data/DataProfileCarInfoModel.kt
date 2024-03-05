package com.etu.ridesharing.data


object DataProfileCarInfoModel {
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
    )
}