package com.etu.ridesharing.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etu.ridesharing.R
import com.etu.ridesharing.data.TravelHistoryList
import com.etu.ridesharing.data.TravelHistoryState
import com.etu.ridesharing.data.UserState
import com.etu.ridesharing.models.DriveModel
import com.etu.ridesharing.ui.components.CarCard
import com.etu.ridesharing.ui.components.TravelHistoryCard


@Composable
fun DriveScreen(
    //actionFunction:()->Unit,
    //user
    onBackStrack: () -> Unit,
    driveModel: DriveModel,
    user: UserState,
    modifier: Modifier = Modifier
) {
    val uiState by driveModel.uiState.collectAsState()
    var travel = TravelHistoryState()
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
                    text = stringResource(id = R.string.driverFIO, uiState.driverName),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            CarCard(uiState.carInfoState, modifier = Modifier)
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
                    text = stringResource(id = R.string.drivePrice, uiState.driveInfoState.price),
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
            }) {
                Text(fontSize = 20.sp, text = "Связаться с водителем")
            }
        }
    }
}