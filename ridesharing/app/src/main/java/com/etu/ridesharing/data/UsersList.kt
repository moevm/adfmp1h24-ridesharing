package com.etu.ridesharing.data

import com.etu.ridesharing.models.UserModel

object UsersList {
    val usersList = mutableListOf<UserState>(
            UserState(
                cars = DataCarInfoList.carList,
                name = "Уолтер",
                surname = "Уайт",
                phoneNumber = "+9-999-999-99-99",
                historyDrives = TravelHistoryList.travelList,
                userDrives = DataDriveInfoList.driveList,
            ),
            UserState(
            cars = DataCarInfoList.carList,
            name = "Райан",
            surname = "Гослинг",
            phoneNumber = "+7-912-212-45-35",
            historyDrives = TravelHistoryList.travelList,
            userDrives = DataDriveInfoList.driveList,
        ),
        UserState(
            cars = DataCarInfoList.carList,
            name = "Патрик",
            surname = "Бейтман",
            phoneNumber = "+7-901-176-12-22",
            historyDrives = TravelHistoryList.travelList,
            userDrives = DataDriveInfoList.driveList,
        ),
        UserState(
            cars = DataCarInfoList.carList,
            name = "Томас",
            surname = "Шелби",
            phoneNumber = "+7-901-111-14-47",
            historyDrives = TravelHistoryList.travelList,
            userDrives = DataDriveInfoList.driveList,
        ),
        UserState(
            cars = DataCarInfoList.carList,
            name = "Тайлер",
            surname = "Дерден",
            phoneNumber = "+7-914-206-11-73",
            historyDrives = TravelHistoryList.travelList,
            userDrives = DataDriveInfoList.driveList,
        ),
    )
}