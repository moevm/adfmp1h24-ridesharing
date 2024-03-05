package com.etu.ridesharing.data

import com.etu.ridesharing.models.CarInfoModel


object DataProfileCarInfoModel {
    val carList = mutableListOf<CarInfoModel>(
        CarInfoModel(CarInfoState(
            mark = "Toyota",
            color =  "Красный",
            number = "А001АА",
        )),
        CarInfoModel(CarInfoState(
            mark = "Volkswagen",
            color =  "Оранжевый",
            number = "В002ВВ",
        )),
    )
}