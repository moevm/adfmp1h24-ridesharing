package com.etu.ridesharing.data

import com.etu.ridesharing.models.DriveModel

object DataDriveList {
    val driveList = mutableListOf<DriveModel>(
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[0],
                carInfoState = DataCarInfoList.carList[0],
                driverName = "Райан Гослинг"
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[1],
                carInfoState = DataCarInfoList.carList[1],
                driverName = "Томас Шелби"
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[2],
                carInfoState = DataCarInfoList.carList[2],
                driverName = "Патрик Бейтман",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[3],
                carInfoState = DataCarInfoList.carList[3],
                driverName = "Тайлер Дерден",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[4],
                carInfoState = DataCarInfoList.carList[4],
                driverName = "Уолтер Уайт",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[5],
                carInfoState = DataCarInfoList.carList[5],
                driverName = "Джесси Пинкман",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[6],
                carInfoState = DataCarInfoList.carList[6],
                driverName = "Густаво Фринг",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[7],
                carInfoState = DataCarInfoList.carList[0],
                driverName = "Лицо со Шрамом",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[8],
                carInfoState = DataCarInfoList.carList[1],
                driverName = "Грегори Хаус",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[9],
                carInfoState = DataCarInfoList.carList[2],
                driverName = "Дерек Виньярд",
            )
        ),
    )
}