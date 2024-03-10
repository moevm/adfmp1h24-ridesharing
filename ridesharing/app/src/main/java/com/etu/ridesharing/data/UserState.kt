package com.etu.ridesharing.data

data class UserState (
    var cars: MutableList<CarInfoState> = mutableListOf(),
    var userDrives: MutableList<DriveInfoState> = mutableListOf(),
    var historyDrives: MutableList<TravelHistoryState> = mutableListOf(),
    var name: String = "",
    var surname: String = "",
    var patronymic: String = "",
    var phoneNumber: String = "",
        )
