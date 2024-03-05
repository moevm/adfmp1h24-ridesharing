package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.data.TravelHistoryList
import com.etu.ridesharing.ui.components.TravelHistoryCard


@Composable @Preview
fun TravelHistory(
    travelList: TravelHistoryList = TravelHistoryList,
    modifier: Modifier = Modifier
){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyColumn(
            modifier = modifier.padding(top = 32.dp)
        ) {
            items(travelList.travelList.size) { driveIndex ->
                if (driveIndex > 0) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                TravelHistoryCard(
                    travel = travelList.travelList[driveIndex],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
            }
        }
    }
}