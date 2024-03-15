package com.etu.ridesharing.ui.components

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
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.etu.ridesharing.data.DriveInfoState
import com.etu.ridesharing.models.DriveInfoModel

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
    var driveDate by rememberSaveable { mutableStateOf("") }
    var driveTime by rememberSaveable { mutableStateOf("") }
    var from by rememberSaveable { mutableStateOf("") }
    var to by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var driveNumberPlaces by rememberSaveable { mutableStateOf("") }
    var score = 10
    val focusManager = LocalFocusManager.current
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
                            text = "Дата:",
                            type = "date",
                            label = { Text("дд/мм/гггг") },
                            value = driveDate,
                            onValueChange = {
                                if (it.length <= maxCharDate) driveDate = it
                            },
                            leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
                        )

                        val maxCharTime = 4
                        CustomTextField(
                            text = "Время:",
                            type = "time",
                            label = { Text("чч:мм") },
                            value = driveTime,
                            onValueChange = {
                                if (it.length <= maxCharTime) driveTime = it
                            },
                        )
                        CustomTextField(
                            text = "Откуда:",
                            type = "text",
                            label = { Text("Город") },
                            value = from,
                            onValueChange = {
                                from = it
                            },
                        )
                        CustomTextField(
                            text = "Куда:",
                            type = "text",
                            label = { Text("Город") },
                            value = to,
                            onValueChange = {
                                to = it
                            },
                        )
                        CustomTextField(
                            text = "Стоимость:",
                            type = "number",
                            label = { Text("Тенге") },
                            value = price,
                            onValueChange = {
                                price = it
                            },
                        )
                        CustomTextField(
                            text = "Кол-во мест:",
                            type = "number",
                            label = { Text("кол-во мест") },
                            value = driveNumberPlaces,
                            onValueChange = {
                                driveNumberPlaces = it
                            },
                        )
                    Button(onClick = {
                        score++
                        actionFunction(driveDate, driveTime,from, to, price.toIntOrNull() ?: 0, driveNumberPlaces.toIntOrNull() ?: 0, score)
                        onDismissRequest() }, modifier = Modifier.padding(start = 64.dp, top = 32.dp)) {
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