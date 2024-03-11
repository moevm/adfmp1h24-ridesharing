package com.etu.ridesharing.data

import java.util.UUID

data class UserState (
    val id: UUID? = UUID.randomUUID(),
    var cars: MutableList<CarInfoState> = mutableListOf(),
    var userDrives: MutableList<DriveInfoState> = mutableListOf(),
    var historyDrives: MutableList<TravelHistoryState> = mutableListOf(),
    var name: String = "",
    var surname: String = "",
    var patronymic: String = "",
    var phoneNumber: String = "",
        )
