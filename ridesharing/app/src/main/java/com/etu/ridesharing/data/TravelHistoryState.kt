package com.etu.ridesharing.data

data class TravelHistoryState(
    val driveDate: String = "",
    val driveTime: String = "",
    val from: String = "",
    val to: String = "",
    val price: Int = 0,
)
