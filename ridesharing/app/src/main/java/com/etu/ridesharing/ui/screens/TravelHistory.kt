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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.data.DataCitiesList
import com.etu.ridesharing.data.DriveInfoState
import com.etu.ridesharing.data.TravelHistoryList
import com.etu.ridesharing.ui.components.AutoCompleteTextField
import com.etu.ridesharing.ui.components.TravelHistoryCard


@Composable @Preview
fun TravelHistory(
    travelList: TravelHistoryList = TravelHistoryList,
    modifier: Modifier = Modifier
){
    var filterSearchFrom by rememberSaveable { mutableStateOf("") }
    var filterSearchTo by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row() {
            Column(
                modifier = Modifier.weight(1.5f).padding(start = 2.dp)
            ) {
                AutoCompleteTextField(categories = DataCitiesList.citiesList, label = "Откуда", value = filterSearchFrom, onValueChange = {filterSearchFrom = it})
            }
            Column(
                modifier = Modifier.weight(0.1f)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
            }
            Column(
                modifier = Modifier.weight(1.5f).padding(end = 2.dp)
            ) {
                AutoCompleteTextField(categories = DataCitiesList.citiesList, label = "Куда", value = filterSearchTo, onValueChange = {filterSearchTo = it})
            }

        }

        LazyColumn(
            modifier = modifier.padding(top = 32.dp)
        ) {
            var filtered = travelList.travelList.filter {
                filterFun(
                    DriveInfoState(
                        from = it.from,
                        to = it.to
                    ),
                    cityFrom = filterSearchFrom,
                    cityTo = filterSearchTo,
                )
            }
            items(filtered.size) { driveIndex ->
                if (driveIndex > 0) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                TravelHistoryCard(
                    travel = filtered[driveIndex],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
            }
        }
    }
}