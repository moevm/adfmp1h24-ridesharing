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
                Spacer(modifier = Modifier.height(24.dp))
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
                Spacer(modifier = Modifier.height(24.dp))
                CarCard(carInfo = userDrive.cars[0] ?: CarInfoState(), modifier = Modifier)
                Spacer(modifier = Modifier.height(24.dp))
                TravelHistoryCard(
                    travel = TravelHistoryState(
                        driveDate = uiState.driveInfoState.driveDate,
                        driveTime = uiState.driveInfoState.driveTime,
                        from = uiState.driveInfoState.from,
                        to = uiState.driveInfoState.to,
                        price = uiState.driveInfoState.price,
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
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
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    travel.driveDate = uiState.driveInfoState.driveDate
                    travel.driveTime = uiState.driveInfoState.driveTime
                    travel.from = uiState.driveInfoState.from
                    travel.to = uiState.driveInfoState.to
                    travel.price = uiState.driveInfoState.price
                    user.historyDrives.add(travel)
                    onBackStrack()
                }
                ) {
                    Text(fontSize = 20.sp, text = "Связаться с водителем")
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    onItemClick2(uiState.driveInfoState.from, uiState.driveInfoState.to)
                }) {
                    Text(fontSize = 20.sp, text = "Все поездки по данному маршруту")
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    onItemClick1(userDrive.id.toString())
                }) {
                    Text(fontSize = 20.sp, text = "Все поездки данного водителя")
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

