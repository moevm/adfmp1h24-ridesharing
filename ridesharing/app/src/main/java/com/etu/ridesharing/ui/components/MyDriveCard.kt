package com.etu.ridesharing.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.etu.ridesharing.R
import com.etu.ridesharing.data.CarInfoState
import com.etu.ridesharing.data.CarSelectOptions
import com.etu.ridesharing.data.DataCitiesList
import com.etu.ridesharing.data.DriveInfoState
import com.etu.ridesharing.models.DriveInfoModel
import com.etu.ridesharing.ui.screens.checkValue
import java.util.Calendar

@Composable
fun MyDriveCard(
    driveInfoModel: DriveInfoModel = viewModel(),
    onDeleteItem: () -> Unit,
    onEditItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by driveInfoModel.uiState.collectAsState()
    val openAlertDialog = remember { mutableStateOf(false) }
    when {
        // ...
        openAlertDialog.value -> {
            MyDriveDialog(
                driveState = uiState,
                actionFunction = { driveDate, driveTime, from, to, price, numberPlaces,driveId ->
                    uiState.driveDate = driveDate
                    uiState.driveTime= driveTime
                    uiState.from= from
                    uiState.to=to
                    uiState.price=price
                    uiState.numberPlaces= numberPlaces
                    uiState.driveId= driveId
                },
                onDismissRequest = { openAlertDialog.value = false }
            )
        }
    }
    Card(modifier = Modifier) {
        val uiState by driveInfoModel.uiState.collectAsState()
        Row() {
            Column(modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))) {
                Text(text = stringResource(id = R.string.date_and_time,uiState.driveDate,uiState.driveTime))
                Text(text = stringResource(id = R.string.from,uiState.from))
                Text(text = stringResource(id = R.string.to,uiState.to))
                Text(text = stringResource(id = R.string.drivePrice,uiState.price))
                Text(text = stringResource(id = R.string.numberPlaces,uiState.numberPlaces))
            }
            Column() {
                IconButton(onClick = onDeleteItem) {
                    Icon(Icons.Outlined.Close, contentDescription = "Localized description")
                }
                IconButton(onClick = {openAlertDialog.value = true}) {
                    Icon(Icons.Outlined.Edit, contentDescription = "Localized description")
                }
            }
        }
    }
}

@Composable
fun MyDriveDialog(
    actionFunction: (driveDate: String,driveTime: String, from: String,to: String,price: Int,numberPlaces: Int,driveId:Int) -> Unit,
    driveState: DriveInfoState = DriveInfoState(),
    onDismissRequest: () -> Unit,
) {
    var driveDate by rememberSaveable { mutableStateOf(driveState.driveDate) }
    var driveTime by rememberSaveable { mutableStateOf(driveState.driveTime) }
    var from by rememberSaveable { mutableStateOf(driveState.from) }
    var to by rememberSaveable { mutableStateOf(driveState.to) }
    var price by rememberSaveable { mutableStateOf("") }
    var numberPlaces by rememberSaveable { mutableStateOf("") }
    var score = 9
    var isErrorData by rememberSaveable { mutableStateOf(false) }
    var isErrorTime by rememberSaveable { mutableStateOf(false) }
    var isErrorTo by rememberSaveable { mutableStateOf(false) }
    var isErrorFrom by rememberSaveable { mutableStateOf(false) }
    var isErrorPrice by rememberSaveable { mutableStateOf(false) }
    var isErrorNumber by rememberSaveable { mutableStateOf(false) }
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

    val DatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            driveDate = "${mDayOfMonth.toString().padStart(2,'0')}${(mMonth+1).toString().padStart(2,'0')}$mYear"
        }, mYear, mMonth, mDay
    )


    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(){
                Column(
                    modifier = Modifier.weight(0.7f).padding(start = 16.dp, top = 32.dp)
                ) {
                        val maxCharDate = 8
                        CustomTextField(
                            isError = isErrorData,
                            supportingText = {
                                if (isErrorData){
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Неправильная дата",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            text = "Дата:",
                            type = "date",
                            label = { Text("дд/мм/гггг") },
                            value = driveDate,
                            onValueChange = {
                                if (it.length <= maxCharDate){
                                    driveDate = it
                                }
                                isErrorData = driveDate.length == 8 && !checkValue(driveDate)
                            },
                            leadIcon = { IconButton(onClick = { DatePickerDialog.show() }) {
                                Icon(Icons.Filled.DateRange, contentDescription = "Localized description")
                            } }
                        )

                        val maxCharTime = 4
                        CustomTextField(
                            isError = isErrorTime,
                            supportingText = {
                                if (isErrorTime){
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Неправильное время",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            text = "Время:",
                            type = "time",
                            label = { Text("чч:мм") },
                            value = driveTime,
                            onValueChange = {
                                if (it.length <= maxCharTime){
                                    driveTime = it
                                }
                                isErrorTime = driveTime.length == 4 && !checkTime(driveTime)
                            },
                        )
                        AutoCompleteTextField(
                            isError = isErrorFrom,
                            onValueChange = {
                                from = it
                                isErrorFrom = false
                            },
                            supportingText = {
                                if (isErrorFrom) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Нужно заполнить поле",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            value = from,
                            label = "Откуда",
                            categories = DataCitiesList.citiesList
                        )
                        AutoCompleteTextField(
                            isError = isErrorTo,
                            onValueChange = {
                                to = it
                                isErrorTo = false
                            },
                            supportingText = {
                                if (isErrorTo) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Нужно заполнить поле",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            value = to,
                            label = "Куда",
                            categories = DataCitiesList.citiesList
                        )
                        CustomTextField(
                            isError = isErrorPrice,
                            supportingText = {
                                if (isErrorPrice){
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Неправильная цена",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            text = "Стоимость:",
                            type = "number",
                            label = { Text("Тенге") },
                            value = price,
                            onValueChange = {
                                price = it
                                isErrorPrice = false
                            },
                        )
                        CustomTextField(
                            isError = isErrorNumber,
                            supportingText = {
                                if (isErrorNumber){
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "Неправильное кол-во мест",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            },
                            text = "Кол-во мест:",
                            type = "number",
                            label = { Text("кол-во мест") },
                            value = numberPlaces,
                            onValueChange = {
                                numberPlaces = it
                                isErrorNumber = false
                            },
                        )
                    Button(onClick = {
                        //Дата
                        if(!(driveDate.length == 8 && !checkValue(driveDate)))
                            isErrorData = driveDate.length < 8 && driveDate.length>0
                        if(driveDate=="")
                            isErrorData = true
                        //Время
                        if(!(driveTime.length == 4 && !checkTime(driveTime)))
                            isErrorTime = driveTime.length < 4 && driveTime.length>0
                        if(driveTime=="")
                            isErrorTime = true
                        //Откуда
                        isErrorFrom = from ==""
                        //Куда
                        isErrorTo = to ==""
                        //Цена
                        isErrorPrice = price.toIntOrNull() ?: 0 <= 0
                        //Места
                        isErrorNumber = numberPlaces.toIntOrNull() ?: 0 <= 0

                        if(!isErrorData and !isErrorTime and !isErrorFrom and !isErrorTo and !isErrorPrice and !isErrorNumber){
                            score++
                            actionFunction(driveDate.substring(0, 2)+"."+driveDate.substring(2, 4)+"."+driveDate.substring(4),
                                driveTime.substring(0, 2)+":"+driveTime.substring(2),
                                from, to, price.toIntOrNull() ?: 0, numberPlaces.toIntOrNull() ?: 0, score)
                            onDismissRequest()
                        } }, modifier = Modifier.padding(start = 64.dp, top = 32.dp)) {
                        Text("Сохранить")
                    }
                }
                Column(modifier = Modifier.weight(0.2f).padding(start = 16.dp)) {
                    IconButton(onClick = { onDismissRequest() }, modifier = Modifier.size(R.dimen.padding_medium.dp)) {
                        Icon(Icons.Outlined.Close, contentDescription = "Localized description")
                    }
                }
            }
        }
    }
}
fun checkTime(date : String) : Boolean{
    if (date.isEmpty() || date.length < 4)
        return false
    return date.slice(0..1).toInt() in (0..23) && date.slice(2..3).toInt() in (0..59)
}