package com.etu.ridesharing.data

import com.etu.ridesharing.models.DriveModel

object DataDriveList {
    val driveList = mutableListOf<DriveModel>(
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[0].uiState.value,
                carInfoState = DataCarInfoList.carList[0],
                driverName = "Райан Гослинг"
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[1].uiState.value,
                carInfoState = DataCarInfoList.carList[1],
                driverName = "Томас Шелби"
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[2].uiState.value,
                carInfoState = DataCarInfoList.carList[2],
                driverName = "Патрик Бейтман",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[3].uiState.value,
                carInfoState = DataCarInfoList.carList[3],
                driverName = "Тайлер Дерден",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[4].uiState.value,
                carInfoState = DataCarInfoList.carList[4],
                driverName = "Уолтер Уайт",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[5].uiState.value,
                carInfoState = DataCarInfoList.carList[5],
                driverName = "Джесси Пинкман",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[6].uiState.value,
                carInfoState = DataCarInfoList.carList[6],
                driverName = "Густаво Фринг",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[7].uiState.value,
                carInfoState = DataCarInfoList.carList[0],
                driverName = "Лицо со Шрамом",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[8].uiState.value,
                carInfoState = DataCarInfoList.carList[1],
                driverName = "Грегори Хаус",
            )
        ),
        DriveModel(
            DriveState(
                driveInfoState = DataDriveInfoList.driveList[9].uiState.value,
                carInfoState = DataCarInfoList.carList[2],
                driverName = "Дерек Виньярд",
            )
        ),
    )
}