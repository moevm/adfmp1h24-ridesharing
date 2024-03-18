package com.etu.ridesharing.ui.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.R
import com.etu.ridesharing.ui.components.CustomTextField
import java.util.Calendar
import kotlin.math.abs

@Composable
fun AdminPageStats(textFrom: String, textTo: String, onChange1: (String)->Unit, onChange2: (String)->Unit) {
    val focusManager = LocalFocusManager.current

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    val DatePickerDialog1 = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onChange1("${mDayOfMonth.toString().padStart(2,'0')}${(mMonth+1).toString().padStart(2,'0')}$mYear")
        }, mYear, mMonth, mDay
    )

    val DatePickerDialog2 = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onChange2("${mDayOfMonth.toString().padStart(2,'0')}${(mMonth+1).toString().padStart(2,'0')}$mYear")
        }, mYear, mMonth, mDay
    )


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
        Row(verticalAlignment = Alignment.CenterVertically) {
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
                //leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
            )
            IconButton(onClick = { DatePickerDialog1.show() }) {
                Icon(Icons.Filled.DateRange, contentDescription = "Localized description")
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
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
            IconButton(onClick = { DatePickerDialog2.show() }) {
                Icon(Icons.Filled.DateRange, contentDescription = "Localized description")
            }
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

fun compareDates(first : String, second : String) : Boolean{
    if (!checkValue(first) || !checkValue(second))
        return false
    return first.slice(4..7).toInt() <= second.slice(4..7).toInt() && first.slice(2..3).toInt() <= second.slice(2..3).toInt() && first.slice(0..1).toInt() <= second.slice(0..1).toInt()
}

fun calcUsers(dateFrom : String, dateTo : String): Int {
    if (!compareDates(dateFrom, dateTo)){
        return 54789
    }
    val dateFromInt = dateFrom.reversed().toInt()
    val dateToInt = dateTo.reversed().toInt()

    return abs(dateFromInt-dateToInt) % 54789 + 345
}

fun calcRides(dateFrom : String, dateTo : String): Int {
    if (!compareDates(dateFrom, dateTo)){
        return 3124
    }
    val dateFromInt = dateFrom.reversed().toInt()
    val dateToInt = dateTo.reversed().toInt()

    return (abs(dateFromInt-dateToInt) % 54789 + 345) /2
}

fun calcDistance(dateFrom : String, dateTo : String): Int {
    if (!compareDates(dateFrom, dateTo)){
        return 312465
    }
    val dateFromInt = dateFrom.reversed().toInt()
    val dateToInt = dateTo.reversed().toInt()

    return (abs((dateFromInt-dateToInt)*49)) % 312465 + 32
}