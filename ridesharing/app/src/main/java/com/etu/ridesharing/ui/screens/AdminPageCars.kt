package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.R
import com.etu.ridesharing.data.DataCarInfoList
import com.etu.ridesharing.ui.components.CarCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable @Preview
fun AdminPageCars(
    carsList: DataCarInfoList = DataCarInfoList,
    modifier: Modifier = Modifier
) {
    var textMark by rememberSaveable { mutableStateOf("") }
    var textNumber by rememberSaveable { mutableStateOf("") }
    var textColor by rememberSaveable { mutableStateOf("") }


    var sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    Column(modifier.fillMaxWidth().fillMaxHeight()) {
        Button(
            modifier = Modifier
                .padding(25.dp, 30.dp)
                .fillMaxWidth(),
            onClick = { isSheetOpen = true}
        ){Text("Выбрать фильтры")}
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(carsList.carList.size) { carInd ->
                if (carInd > 0) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                CarCard(
                    carInfo = carsList.carList[carInd],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
            }
        }
    }



    if (isSheetOpen){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { isSheetOpen = false }
        ) {
            Button(
                modifier = Modifier
                    .padding(25.dp, 10.dp)
                    .fillMaxWidth(),
                onClick = {}
            ){Text("Применить фильтры")}
            Column(modifier = Modifier){
                Row(modifier = Modifier.padding(25.dp, 10.dp)){
                    Text(text = stringResource(id = R.string.mark))
                    TextField(value = textMark, onValueChange = {}, singleLine = true)
                }
                Row(modifier = Modifier.padding(25.dp, 10.dp)){
                    Text(text = stringResource(id = R.string.number))
                    TextField(value = textNumber, onValueChange = {}, singleLine = true)

                }
                Row(modifier = Modifier.padding(25.dp, 10.dp)){
                    Text(text = stringResource(id = R.string.color))
                    TextField(value = textColor, onValueChange = {}, singleLine = true)

                }
            }
        }
    }
}

