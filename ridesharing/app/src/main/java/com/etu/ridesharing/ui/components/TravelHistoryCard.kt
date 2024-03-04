package com.etu.ridesharing.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
    Card(modifier = Modifier) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = dimensionResource(R.dimen.padding_medium))) {
            Text(fontSize = 18.sp,text = stringResource(id = R.string.date_and_time, getDateInfo(travel.driveDate),travel.driveTime))
            Text(fontSize = 18.sp,text = stringResource(id = R.string.companionCardPrice,travel.price), modifier = Modifier.padding(end = 16.dp))
            Text(fontSize = 18.sp,text = stringResource(id = R.string.companionCardCities,travel.from,travel.to))
        }
    }
}