package com.etu.ridesharing.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.etu.ridesharing.R
import com.etu.ridesharing.data.CarInfoState

@Composable @Preview
fun CarCard(
    carInfo: CarInfoState = CarInfoState(),
    modifier: Modifier = Modifier
){
    Card(modifier = Modifier.fillMaxWidth()) {
                Text(fontSize = 26.sp,text = stringResource(id = R.string.mark,carInfo.mark), modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(fontSize = 26.sp,text = stringResource(id = R.string.number, carInfo.number), modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(fontSize = 26.sp,text = stringResource(id = R.string.color,carInfo.color), modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}