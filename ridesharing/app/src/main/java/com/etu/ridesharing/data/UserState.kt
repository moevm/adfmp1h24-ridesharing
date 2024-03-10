package com.etu.ridesharing.data

data class UserState (
    val cars: MutableList<CarInfoState> = mutableListOf(),
    val userDrives: MutableList<DriveInfoState> = mutableListOf(),
    val historyDrives: MutableList<TravelHistoryState> = mutableListOf(),
    val name: String = "",
    val surname: String = "",
    val patronymic: String = "",
    val phoneNumber: String = "",
        )
