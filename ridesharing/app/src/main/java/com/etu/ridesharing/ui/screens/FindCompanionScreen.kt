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
import com.etu.ridesharing.data.DataCitiesList
import com.etu.ridesharing.data.DriveInfoState
import com.etu.ridesharing.models.DriveInfoModel
import com.etu.ridesharing.ui.components.AutoCompleteTextField
import com.etu.ridesharing.ui.components.CustomTextField
import com.etu.ridesharing.ui.components.FindCompanionCard

@Composable
fun FindCompanionScreen(
    companionDrivesList: MutableList<DriveInfoState>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    when {
        openAlertDialog.value -> {
            FindCompanionDialog(
                onDismissRequest = { openAlertDialog.value = false },
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
                    AutoCompleteTextField(categories = DataCitiesList.citiesList, label = "Откуда")
                }
                Column(
                    modifier = Modifier.weight(0.1f)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Column(
                    modifier = Modifier.weight(1.5f).padding(end = 2.dp)
                ) {
                    AutoCompleteTextField(categories = DataCitiesList.citiesList, label = "Куда")
                }

            }
            LazyColumn(modifier = Modifier.padding(top = 32.dp)) {
                items(companionDrivesList.size) { driveIndex ->
                    if (driveIndex > 0) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    FindCompanionCard(
                        onItemClick = onItemClick,
                        driveInfoModel = DriveInfoModel( companionDrivesList[driveIndex]),
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
) {
    var filterDateFrom by rememberSaveable { mutableStateOf("") }
    var filterDateTo by rememberSaveable { mutableStateOf("") }
    var filterPriceLow by rememberSaveable { mutableStateOf("") }
    var filterPriceHigh by rememberSaveable { mutableStateOf("") }
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
                        type = "dateTime",
                        label = { Text("чч:мм дд/мм/гггг") },
                        value = filterDateFrom,
                        onValueChange = {
                            if (it.length <= maxCharDate) filterDateFrom = it
                        },
                        leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
                    )
                    CustomTextField(
                        text = "до:",
                        type = "dateTime",
                        label = { Text("чч:мм дд/мм/гггг") },
                        value = filterDateTo,
                        onValueChange = {
                            if (it.length <= maxCharDate) filterDateTo = it
                        },
                        leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
                    )
                    Text(text = "Цена:")
                    CustomTextField(
                        text = "От:",
                        type = "number",
                        label = { Text("тенге") },
                        value = filterPriceLow,
                        onValueChange = {
                            filterPriceLow = it
                        },
                    )
                    CustomTextField(
                        text = "До:",
                        type = "number",
                        label = { Text("тенге") },
                        value = filterPriceHigh,
                        onValueChange = {
                            filterPriceHigh = it
                        },
                    )
                    Button(onClick = { onDismissRequest() }, modifier = Modifier.padding( top = 16.dp, start = 36.dp, bottom = 16.dp)) {
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