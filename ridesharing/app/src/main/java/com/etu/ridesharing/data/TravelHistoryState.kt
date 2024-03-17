package com.etu.ridesharing.data

data class TravelHistoryState(
    var driveDate: String = "",
    var driveTime: String = "",
    var from: String = "",
    var to: String = "",
    var price: Int = 0,
)
