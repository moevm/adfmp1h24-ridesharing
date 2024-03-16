package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.etu.ridesharing.R
import com.etu.ridesharing.data.CarInfoState
import com.etu.ridesharing.data.DataCitiesList
import com.etu.ridesharing.data.DriveInfoState
import com.etu.ridesharing.models.DriveInfoModel
import com.etu.ridesharing.ui.components.AutoCompleteTextField
import com.etu.ridesharing.ui.components.CarCard
import com.etu.ridesharing.ui.components.CustomTextField
import com.etu.ridesharing.ui.components.FindCompanionCard

@Composable
fun FindCompanionScreen(
    companionDrivesList: MutableList<DriveInfoState>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    var filterDateFrom by rememberSaveable { mutableStateOf("") }
    var filterDateTo by rememberSaveable { mutableStateOf("") }
    var filterPriceLow by rememberSaveable { mutableStateOf("") }
    var filterPriceHigh by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    when {
        openAlertDialog.value -> {
            FindCompanionDialog(
                onDismissRequest = { openAlertDialog.value = false },
                filterDateFrom =  filterDateFrom,
                filterDateTo = filterDateTo,
                filterPriceLow = filterPriceLow,
                filterPriceHigh = filterPriceHigh,
                changeValues = { dateFrom,dateTo,priceLow, priceHigh->
                    filterDateFrom = dateFrom
                    filterDateTo = dateTo
                    filterPriceLow =priceLow
                    filterPriceHigh= priceHigh
                },
            )
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            focusManager.clearFocus()
                        }
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Column(
                    modifier = Modifier.weight(1.5f).padding(start = 2.dp)
                ) {
                    //AutoCompleteTextField(categories = DataCitiesList.citiesList, label = "Откуда")
                }
                Column(
                    modifier = Modifier.weight(0.1f)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Column(
                    modifier = Modifier.weight(1.5f).padding(end = 2.dp)
                ) {
                   // AutoCompleteTextField(categories = DataCitiesList.citiesList, label = "Куда")
                }

            }
            LazyColumn(modifier = Modifier.padding(top = 32.dp)) {
                var filtered = companionDrivesList.filter {
                    filterFun(
                        it,
                        filterDateFrom,
                        filterDateTo,
                        filterPriceLow,
                        filterPriceHigh
                    )
                }
                items(filtered.size) { driveIndex ->
                    if (driveIndex > 0) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    FindCompanionCard(
                        onItemClick = onItemClick,
                        driveInfoModel = DriveInfoModel( filtered[driveIndex]),
                        modifier = modifier
                            .size(width = 350.dp, height = 150.dp),
                    )
                }
            }

        }
        FloatingActionButton(
            onClick = { openAlertDialog.value = true },
            modifier = Modifier
                .align(Alignment.BottomEnd) // Закрепляем кнопку в правом нижнем углу
                .padding(16.dp), // Добавляем отступ
            contentColor = Color.Blue,
        ) {
            Icon(painter = painterResource(R.drawable.filter), contentDescription = "", modifier = Modifier.size(size = 46.dp),)
        }
    }
}

@Composable
fun FindCompanionDialog(
    onDismissRequest: () -> Unit,
    filterDateFrom: String,
    filterDateTo: String,
    filterPriceLow: String,
    filterPriceHigh: String,
    changeValues:(String, String, String,String)->Unit,
) {
    var filterDateFrom_temp by rememberSaveable { mutableStateOf(filterDateFrom) }
    var filterDateTo_temp by rememberSaveable { mutableStateOf(filterDateTo) }
    var filterPriceLow_temp by rememberSaveable { mutableStateOf(filterPriceLow) }
    var filterPriceHight_temp by rememberSaveable { mutableStateOf(filterPriceHigh) }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(
            ){
                Column(

                    modifier = Modifier.weight(0.7f).padding(start = 16.dp, top = 32.dp),
                ) {

                    val maxCharDate = 100
                    Text(text = "Дата:")
                    CustomTextField(
                        text = "от:",
                        type = "date",
                        label = { Text("чч:мм дд/мм/гггг") },
                        value = filterDateFrom_temp,
                        onValueChange = {
                            if (it.length <= maxCharDate) filterDateFrom_temp = it
                        },
                        leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
                    )
                    CustomTextField(
                        text = "до:",
                        type = "date",
                        label = { Text("чч:мм дд/мм/гггг") },
                        value = filterDateTo_temp,
                        onValueChange = {
                            if (it.length <= maxCharDate) filterDateTo_temp = it
                        },
                        leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
                    )
                    Text(text = "Цена:")
                    CustomTextField(
                        text = "От:",
                        type = "number",
                        label = { Text("тенге") },
                        value = filterPriceLow_temp,
                        onValueChange = {
                            filterPriceLow_temp = it
                        },
                    )
                    CustomTextField(
                        text = "До:",
                        type = "number",
                        label = { Text("тенге") },
                        value = filterPriceHight_temp,
                        onValueChange = {
                            filterPriceHight_temp = it
                        },
                    )
                    Button(onClick = {
                        changeValues(filterDateFrom_temp, filterDateTo_temp, filterPriceLow_temp,filterPriceHight_temp)
                        onDismissRequest() },
                        modifier = Modifier.padding( top = 16.dp, start = 36.dp, bottom = 16.dp)) {
                        Text("Применить фильтры")
                    }
                }
                Column(modifier = Modifier.weight(0.2f).padding(start = 16.dp)) {
                    IconButton(onClick = { onDismissRequest() }, modifier = Modifier.size(R.dimen.padding_medium.dp)) {
                        Icon(Icons.Outlined.Close, contentDescription = "Localized description")
                    }
                }
            }
        }
    }
}

fun filterFun(item : DriveInfoState, from : String, to : String, priceLow : String, priceHight : String) : Boolean{
    var flag1 = false
    var flag2 = false
    var flag3 = false
    var flag4 = false

    if(from == "") {
        flag1 = true
    }
    if(to == ""){
        flag2 = true
    }
    if((priceLow == "")or(item.price >= priceLow.toIntOrNull() ?: 0)){
        flag3 = true
    }
    if((priceHight == "")or(item.price <= priceHight.toIntOrNull() ?: 0)){
        flag4 = true
    }
    return flag1 and flag2 and flag3 and flag4//(item.price > priceLow.toIntOrNull() ?: 0)//and (item.price <= priceHight.toIntOrNull() ?: 0)
}