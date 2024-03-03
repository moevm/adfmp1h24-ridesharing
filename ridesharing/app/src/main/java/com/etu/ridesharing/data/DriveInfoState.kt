package com.etu.ridesharing.data

import java.util.Date

data class DriveInfoState(
    val driveDate: String = "",
    val driveTime: String = "",
    val from: String = "",
    val to: String = "",
    val price: Int = 0,
    val numberPlaces: Int = 0,
    )