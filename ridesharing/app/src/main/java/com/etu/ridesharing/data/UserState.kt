package com.etu.ridesharing.data

data class UserState (
    var cars: MutableList<CarInfoState> = mutableListOf(),
    var userDrives: MutableList<DriveInfoState> = mutableListOf(),
    val historyDrives: MutableList<TravelHistoryState> = mutableListOf(),
    val name: String = "",
    val surname: String = "",
    val patronymic: String = "",
    val phoneNumber: String = "",
        )
