package com.etu.ridesharing.data

import java.util.Date

data class DriveInfoState(
    var driveDate: String = "",
    var driveTime: String = "",
    var from: String = "",
    var to: String = "",
    var price: Int = 0,
    var numberPlaces: Int = 0,
    var driveId: Int = 0,
    )