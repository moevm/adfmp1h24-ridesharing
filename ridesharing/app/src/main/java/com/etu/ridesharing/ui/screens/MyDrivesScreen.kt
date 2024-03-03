package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.models.DriveInfoModel
import com.etu.ridesharing.ui.components.MyDriveCard

@Composable
fun MyDrivesScreen(
    myDrivesList: MutableList<DriveInfoModel>,
    onRemoveDrive: (DriveInfoModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(myDrivesList.size) { driveIndex ->
            if (driveIndex > 0) {
                Spacer(modifier = Modifier.height(16.dp))
            }
            MyDriveCard(
                driveInfoModel = myDrivesList[driveIndex],
                modifier = modifier,
                onEditItem = {},
                onDeleteItem = {onRemoveDrive(myDrivesList[driveIndex])},
            )
        }
    }
}
