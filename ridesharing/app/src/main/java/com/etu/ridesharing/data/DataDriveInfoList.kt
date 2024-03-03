package com.etu.ridesharing.data

import com.etu.ridesharing.models.DriveInfoModel

object DataDriveInfoList {
    val driveList = mutableListOf<DriveInfoModel>(
        DriveInfoModel(DriveInfoState(
            driveDate = "01.02.24",
            driveTime =  "21:30",
            from = "Алма-Ата",
            to = "Астана",
            price = 1500,
            numberPlaces = 2,
        )),
        DriveInfoModel(DriveInfoState(
            driveDate = "02.02.24",
            driveTime =  "19:00",
            from = "Астана",
            to = "Актобе",
            price = 2000,
            numberPlaces = 2,
        )),
        DriveInfoModel(DriveInfoState(
            driveDate = "03.02.24",
            driveTime =  "15:30",
            from = "Караганда",
            to = "Тараз",
            price = 2500,
            numberPlaces = 3,
        )),
        DriveInfoModel(DriveInfoState(
            driveDate = "03.02.24",
            driveTime =  "12:30",
            from = "Костанай",
            to = "Туркестан",
            price = 1000,
            numberPlaces = 2,
        )),
        DriveInfoModel(DriveInfoState(
            driveDate = "04.02.24",
            driveTime =  "16:30",
            from = "Актау",
            to = "Семей",
            price = 1200,
            numberPlaces = 1,
        )),
        DriveInfoModel(DriveInfoState(
            driveDate = "05.02.24",
            driveTime =  "13:00",
            from = "Семей",
            to = "Костанай",
            price = 800,
            numberPlaces = 3,
        )),
        DriveInfoModel(DriveInfoState(
            driveDate = "06.02.24",
            driveTime =  "11:00",
            from = "Костанай",
            to = "Астана",
            price = 1400,
            numberPlaces = 2,
        )),
        DriveInfoModel(DriveInfoState(
            driveDate = "06.02.24",
            driveTime =  "15:30",
            from = "Актобе",
            to = "Алма-Ата",
            price = 700,
            numberPlaces = 3,
        )),
        DriveInfoModel(DriveInfoState(
            driveDate = "06.02.24",
            driveTime =  "16:30",
            from = "Алма-Ата",
            to = "Туркестан",
            price = 1000,
            numberPlaces = 2,
        )),
        DriveInfoModel(DriveInfoState(
            driveDate = "08.02.24",
            driveTime =  "10:30",
            from = "Туркестан",
            to = "Караганда",
            price = 1600,
            numberPlaces = 2,
        )),
    )
}