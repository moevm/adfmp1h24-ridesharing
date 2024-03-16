package com.etu.ridesharing.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.etu.ridesharing.R
import com.etu.ridesharing.models.DriveInfoModel
import java.util.Calendar

@Composable
fun FindCompanionCard(
    onItemClick: (Int) -> Unit,
    driveInfoModel: DriveInfoModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        val uiState by driveInfoModel.uiState.collectAsState()
        Column() {
            Row() {
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(R.dimen.padding_medium))) {
                    Text(fontSize = 18.sp,text = stringResource(id = R.string.date_and_time, uiState.driveDate,uiState.driveTime))
                    Text(fontSize = 18.sp,text = stringResource(id = R.string.companionCardCities,uiState.from,uiState.to))
                }
                Column() {
                    Text(fontSize = 18.sp,text = stringResource(id = R.string.companionCardPrice,uiState.price), modifier = Modifier.padding(end = 16.dp))
                    Row(modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))) {
                        Text(fontSize = 36.sp,text = uiState.numberPlaces.toString())
                        Icon(Icons.Filled.Person, contentDescription = "", modifier = Modifier.size(48.dp))
                    }

                }
            }
            Row(

            ){
                Button(onClick = {onItemClick(uiState.driveId)}
                    , modifier = Modifier.size(width = 200.dp, height = 60.dp)
                        .padding(start = 20.dp, top = 10.dp)) {
                    Text("Просмотреть")
                }
            }

        }

    }
}



fun getDateInfo(currentDate: String): String {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    return "${day}.${month}.${year}"
}