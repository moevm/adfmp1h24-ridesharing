package com.etu.ridesharing.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etu.ridesharing.R
import com.etu.ridesharing.data.TravelHistoryState

@Composable @Preview
fun TravelHistoryCard(
    travel: TravelHistoryState = TravelHistoryState(),
    modifier: Modifier = Modifier
){
    Card(modifier = Modifier.fillMaxWidth(0.9f)) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(fontSize = 26.sp,text = stringResource(id = R.string.date_and_time, travel.driveDate,travel.driveTime), modifier = Modifier.fillMaxWidth().padding(top=8.dp), textAlign= TextAlign.Center)
            Text(fontSize = 26.sp,text = stringResource(id = R.string.companionCardPrice,travel.price), modifier = Modifier.padding(top = 16.dp, bottom = 16.dp).fillMaxWidth(), textAlign= TextAlign.Center)
            Text(fontSize = 26.sp,text = stringResource(id = R.string.companionCardCities,travel.from,travel.to), modifier = Modifier.fillMaxWidth().padding(bottom=8.dp), textAlign= TextAlign.Center)
        }
    }
}