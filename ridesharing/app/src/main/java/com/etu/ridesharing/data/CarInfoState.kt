package com.etu.ridesharing.data

import java.util.UUID

data class CarInfoState(
    val id: UUID? = UUID.randomUUID(),
    var mark: String = "",
    var color: String = "",
    var number: String = ""
)
