package com.etu.ridesharing.data

object TravelHistoryList {
    val travelList = mutableListOf<TravelHistoryState>(
        TravelHistoryState(
            driveDate = "01.02.2024",
            driveTime =  "21:30",
            from = "Астана",
            to = "Алма-Ата",
            price = 1500,
        ),
        TravelHistoryState(
            driveDate = "02.02.2024",
            driveTime =  "20:00",
            from = "Алма-Ата",
            to = "Актау",
            price = 1400,
        ),
        TravelHistoryState(
            driveDate = "03.02.2024",
            driveTime =  "19:30",
            from = "Актау",
            to = "Актобе",
            price = 1200,
        ),
        TravelHistoryState(
            driveDate = "04.02.2024",
            driveTime =  "19:00",
            from = "Актобе",
            to = "Астана",
            price = 1300,
        ),
        TravelHistoryState(
            driveDate = "05.02.2024",
            driveTime =  "05:30",
            from = "Астана",
            to = "Караганда",
            price = 1000,
        ),
        TravelHistoryState(
            driveDate = "06.02.2024",
            driveTime =  "22:55",
            from = "Караганда",
            to = "Актау",
            price = 1100,
        ),
    )
}