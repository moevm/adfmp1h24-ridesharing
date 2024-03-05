package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etu.ridesharing.R
import com.etu.ridesharing.models.DriveInfoModel
import com.etu.ridesharing.models.DriveModel
import com.etu.ridesharing.ui.components.CarCard
import com.etu.ridesharing.ui.theme.Purple40
import com.etu.ridesharing.ui.theme.Purple80
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import com.etu.ridesharing.ui.theme.PurpleGrey80
@Composable
fun DriveScreen(
    driveModel: DriveModel,
    modifier: Modifier = Modifier
) {
    val uiState by driveModel.uiState.collectAsState()
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.width(300.dp).fillMaxHeight()
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
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    fontSize = 26.sp,
                    text = stringResource(id = R.string.drivePrice, uiState.driveInfoState.price,),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {}) {
                Text(fontSize = 20.sp,text = "Связаться с водителем")
            }
        }
    }
}