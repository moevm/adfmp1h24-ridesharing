package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.R
import com.etu.ridesharing.models.DriveInfoModel
import com.etu.ridesharing.ui.components.MyDriveCard
import com.etu.ridesharing.ui.components.MyDriveDialog
import com.etu.ridesharing.data.DriveInfoState
import com.etu.ridesharing.data.DriveState
import com.etu.ridesharing.data.UserState
import com.etu.ridesharing.models.DriveModel

@Composable
fun MyDrivesScreen(
    user: UserState,
    openDialog: Boolean = false,
    modifier: Modifier = Modifier
) {
    val openAlertDialog = remember { mutableStateOf(openDialog) }
    val driveState = DriveInfoState()
    val compDriveModel = DriveModel()
    val drivesState = remember { mutableStateOf(user.userDrives.toMutableList())}
    when {
        // ...
        openAlertDialog.value -> {
            MyDriveDialog(
                driveState = driveState,
                actionFunction = { driveDate, driveTime, from, to, price, numberPlaces,driveId ->
                    driveState.driveDate = driveDate
                    driveState.driveTime= driveTime
                    driveState.from= from
                    driveState.to=to
                    driveState.price=price
                    driveState.numberPlaces= numberPlaces
                    driveState.driveId= driveId
                    user.userDrives.add(driveState)
                    user.userDrives = drivesState.value.toMutableList().apply { add(driveState) }
                    drivesState.value = drivesState.value.toMutableList().apply { add(driveState) }

                },
                onDismissRequest = { openAlertDialog.value = false }
            )
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val removeDrive: (DriveInfoState) -> Unit = { drive ->
            drivesState.value = drivesState.value.toMutableList().apply { remove(drive) }
            user.userDrives = drivesState.value.toMutableList().apply { remove(drive) }
        }
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(drivesState.value) { index, drive ->
                if (index != 0) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                MyDriveCard(
                    driveInfoModel = DriveInfoModel(drive),
                    modifier = modifier,
                    onEditItem = {},
                    onDeleteItem = {
                        removeDrive(drive)
                    },
                )
            }
        }

        FloatingActionButton(
            onClick = { openAlertDialog.value = true },
            modifier = Modifier
                .align(Alignment.BottomEnd) // Закрепляем кнопку в правом нижнем углу
                .padding(16.dp), // Добавляем отступ
            contentColor = Color.Blue,
        ) {
            Icon(
                painter = painterResource(R.drawable.plus_circle),
                contentDescription = "",
                modifier = Modifier.size(size = 46.dp),
            )
        }
    }
}
