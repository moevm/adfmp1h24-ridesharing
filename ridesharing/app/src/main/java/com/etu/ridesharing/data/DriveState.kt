package com.etu.ridesharing.data

data class DriveState(
        val driverName: String = "",
        val driveInfoState: DriveInfoState = DriveInfoState(),
        val carInfoState: CarInfoState = CarInfoState(),
)