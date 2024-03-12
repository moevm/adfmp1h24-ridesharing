package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.ui.components.CustomTextField
import kotlin.math.abs

@Composable
fun AdminPageStats(textFrom: String, textTo: String, onChange1: (String)->Unit, onChange2: (String)->Unit) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(){
            CustomTextField(
                isError = textFrom.length == 8 && !checkValue(textFrom),
                supportingText = {
                     if (textFrom.length == 8 && !checkValue(textFrom)){
                         Text(
                             modifier = Modifier.fillMaxWidth(),
                             text = "Неправильная дата",
                             color = MaterialTheme.colorScheme.error
                         )
                     }
                },
                text = "C:",
                type = "date",
                label = { Text("дд/мм/гггг") },
                value = textFrom,
                onValueChange = {onChange1(it)},
                leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(){
            CustomTextField(
                isError = textTo.length == 8 && !checkValue(textTo),
                supportingText = {
                    if (textTo.length == 8 && !checkValue(textTo)){
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Неправильная дата",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                text = "По:",
                type = "date",
                label = { Text("дд/мм/гггг") },
                value = textTo,
                onValueChange = {onChange2(it)},
                leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
            )
        }

        Column(
            Modifier
                .fillMaxHeight()
                .padding(top = 25.dp)) {
            Row {
                Text(text = "Пользователей",
                    Modifier
                        .fillMaxWidth(0.3F)
                        .padding(top = 25.dp))
                Text(text = "${calcUsers(textFrom, textTo)}",
                    Modifier
                        .fillMaxWidth(0.5F)
                        .padding(top = 25.dp, start = 25.dp))
            }
            Row {
                Text(text = "Всего поездок",
                    Modifier
                        .fillMaxWidth(0.3F)
                        .padding(top = 25.dp))
                Text(text = "${calcRides(textFrom, textTo)}",
                    Modifier
                        .fillMaxWidth(0.5F)
                        .padding(top = 25.dp, start = 25.dp))
            }
            Row {
                Text(text = "Общий пробег",
                    Modifier
                        .fillMaxWidth(0.3F)
                        .padding(top = 25.dp))
                Text(text = "${calcDistance(textFrom, textTo)}",
                    Modifier
                        .fillMaxWidth(0.5F)
                        .padding(top = 25.dp, start = 25.dp))
            }
        }

    }
}

fun checkValue(date : String) : Boolean{
    if (date.isEmpty() || date.length < 8)
        return false
    return date.slice(0..1).toInt() in (1..31) && date.slice(2..3).toInt() in (1..12) && date.slice(4..7).toInt() in (0..2024)
}

fun calcUsers(dateFrom : String, dateTo : String): Int {
    if (!(checkValue(dateFrom) && checkValue(dateTo))){
        return 54789
    }
    val dateFromInt = dateFrom.reversed().toInt()
    val dateToInt = dateTo.reversed().toInt()

    return abs(dateFromInt-dateToInt) % 54789 + 345
}

fun calcRides(dateFrom : String, dateTo : String): Int {
    if (!(checkValue(dateFrom) && checkValue(dateTo))){
        return 3124
    }
    val dateFromInt = dateFrom.reversed().toInt()
    val dateToInt = dateTo.reversed().toInt()

    return (abs(dateFromInt-dateToInt) % 54789 + 345) /2
}

fun calcDistance(dateFrom : String, dateTo : String): Int {
    if (!(checkValue(dateFrom) && checkValue(dateTo))){
        return 312465
    }
    val dateFromInt = dateFrom.reversed().toInt()
    val dateToInt = dateTo.reversed().toInt()

    return abs(dateFromInt-dateToInt)*49 + 32
}