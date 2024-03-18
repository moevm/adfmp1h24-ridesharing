package com.etu.ridesharing.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.etu.ridesharing.R
import com.etu.ridesharing.data.CarInfoState
import com.etu.ridesharing.data.TravelHistoryList
import com.etu.ridesharing.data.TravelHistoryState
import com.etu.ridesharing.data.UserState
import com.etu.ridesharing.models.DriveModel
import com.etu.ridesharing.ui.components.CarCard
import com.etu.ridesharing.ui.components.CustomTextField
import com.etu.ridesharing.ui.components.TravelHistoryCard


@Composable
fun DriveScreen(
    onBackStrack: () -> Unit,
    onItemClick1: (String) -> Unit,
    onItemClick2: (String,String) -> Unit,
    driveModel: DriveModel,
    user: UserState,
    userDrive:UserState,
    modifier: Modifier = Modifier
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val uiState by driveModel.uiState.collectAsState()
    var travel = TravelHistoryState()
    when {
        openAlertDialog.value -> {
            FilterCompanionDialog(
                onDismissRequest = { openAlertDialog.value = false },
                onBackStrack =  onBackStrack,
                onItemClick1 = onItemClick1,
                onItemClick2 = onItemClick2,
                userDrive = userDrive.id.toString(),
                from = uiState.driveInfoState.from,
                to = uiState.driveInfoState.to
            )
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .width(300.dp)
                    .fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.height(150.dp))
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        fontSize = 26.sp,
                        text = stringResource(
                            id = R.string.driverFIO,
                            userDrive.name + " " + userDrive.surname + " " + userDrive.patronymic
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                CarCard(carInfo = userDrive.cars[0] ?: CarInfoState(), modifier = Modifier)
                Spacer(modifier = Modifier.height(32.dp))
                TravelHistoryCard(
                    travel = TravelHistoryState(
                        driveDate = uiState.driveInfoState.driveDate,
                        driveTime = uiState.driveInfoState.driveTime,
                        from = uiState.driveInfoState.from,
                        to = uiState.driveInfoState.to,
                        price = uiState.driveInfoState.price,
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        fontSize = 26.sp,
                        text = stringResource(
                            id = R.string.drivePrice,
                            uiState.driveInfoState.price
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = {
                    travel.driveDate = uiState.driveInfoState.driveDate
                    travel.driveTime = uiState.driveInfoState.driveTime
                    travel.from = uiState.driveInfoState.from
                    travel.to = uiState.driveInfoState.to
                    travel.price = uiState.driveInfoState.price
                    user.historyDrives.add(travel)
                    onBackStrack()
                },
                    modifier = Modifier.padding(end = 28.dp)
                ) {
                    Text(fontSize = 20.sp, text = "Связаться с водителем")
                }
            }
        }
        FloatingActionButton(
            onClick = { openAlertDialog.value = true },
            modifier = Modifier
                .align(Alignment.BottomEnd) // Закрепляем кнопку в правом нижнем углу
                .padding(16.dp), // Добавляем отступ
            contentColor = Color.Blue,
        ) {
            Icon(painter = painterResource(R.drawable.filter), contentDescription = "", modifier = Modifier.size(size = 46.dp),)
        }
    }
}


@Composable
fun FilterCompanionDialog(
    onDismissRequest: () -> Unit,
    onBackStrack: () -> Unit,
    onItemClick1: (String) -> Unit,
    onItemClick2: (String,String) -> Unit,
    to:String,
    from:String,
    userDrive:String

) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(
            ) {
                Column (modifier = Modifier.weight(0.7f).padding(start = 16.dp, top = 32.dp),){
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(onClick = {
                        onItemClick2(from, to)
                        onDismissRequest()
                    }) {
                        Text(fontSize = 20.sp, text = "Все поездки по данному маршруту")
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(onClick = {
                        onItemClick1(userDrive)
                        onDismissRequest()
                    }) {
                        Text(fontSize = 20.sp, text = "Все поездки данного водителя")
                    }
                    Spacer(modifier = Modifier.height(32.dp))
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