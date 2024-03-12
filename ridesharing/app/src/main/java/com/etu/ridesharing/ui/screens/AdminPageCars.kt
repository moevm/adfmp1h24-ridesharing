package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.etu.ridesharing.R
import com.etu.ridesharing.data.CarInfoState
import com.etu.ridesharing.data.CarSelectOptions
import com.etu.ridesharing.data.DataCarInfoList
import com.etu.ridesharing.ui.components.AutoCompleteTextField
import com.etu.ridesharing.ui.components.CarCard
import com.etu.ridesharing.ui.components.CustomTextField
import com.etu.ridesharing.ui.components.MyDriveDialog

@Composable @Preview
fun AdminPageCars(
    carsList: DataCarInfoList = DataCarInfoList,
    modifier: Modifier = Modifier
) {
    var textMark by rememberSaveable { mutableStateOf("") }
    var textNumber by rememberSaveable { mutableStateOf("") }
    var textColor by rememberSaveable { mutableStateOf("") }

    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (isSheetOpen){
        CarFilterDialog(
            textMark = textMark,
            textNumber = textNumber,
            textColor = textColor,
            changeValues = { mark,number,color->
                textMark = mark
                textNumber = number
                textColor = color
            },
            onDismissRequest = { isSheetOpen = false },
        )
    }


    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
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
            var filtered = carsList.carList.filter {
                filterFun(
                    it,
                    textMark,
                    textNumber,
                    textColor
                )
            }
            items(filtered.size) { carInd ->
                if (carInd > 0) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                CarCard(
                    carInfo = filtered[carInd],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
            }
        }
    }
}


@Composable
fun CarFilterDialog(
    textMark : String,
    textNumber : String,
    textColor : String,
    changeValues : (String, String, String)->Unit,
    onDismissRequest: () -> Unit,
) {
    var textMark_temp by rememberSaveable { mutableStateOf(textMark) }
    var textNumber_temp by rememberSaveable { mutableStateOf(textNumber) }
    var textColor_temp by rememberSaveable { mutableStateOf(textColor) }
    val focusManager = LocalFocusManager.current
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(start = 16.dp, top = 32.dp),
                ) {
                    AutoCompleteTextField(
                        supportingText = { },
                        onValueChange = {
                            textMark_temp = it
                        },
                        value = textMark_temp,
                        label = "Марка автомобиля",
                        categories = listOf("Toyota", "Volkswagen", "Ford", "Hyundai", "Honda")
                    )
                    AutoCompleteTextField(
                        supportingText = { },
                        onValueChange = {
                            textNumber_temp = it
                        },
                        value = textNumber_temp,
                        label = "Номер автомобиля",
                        categories = listOf("А001АА", "В002ВВ", "Е003ЕЕ", "К004КК", "М005ММ")
                    )
                    AutoCompleteTextField(
                        supportingText = { },
                        onValueChange = {
                            textColor_temp = it
                        },
                        value = textColor_temp,
                        label = "Цвет автомобиля",
                        categories = listOf("Белый", "Чёрный", "Серый", "Красный", "Синий")
                    )
                    Button(onClick = {
                        changeValues(textMark_temp, textNumber_temp, textColor_temp)
                        onDismissRequest() },
                        modifier = Modifier.padding( top = 16.dp, start = 36.dp, bottom = 16.dp)) {
                        Text("Применить фильтры")
                    }
                }
                Column(modifier = Modifier
                    .weight(0.2f)
                    .padding(end = 8.dp)) {
                    IconButton(onClick = { onDismissRequest() }, modifier = Modifier.size(R.dimen.padding_medium.dp)) {
                        Icon(Icons.Outlined.Close, contentDescription = "Localized description")
                    }
                }
            }
        }
    }
}

fun filterFun(item : CarInfoState, mark : String, number : String, color : String) : Boolean{
    return item.mark.contains(mark, ignoreCase = true) and item.number.contains(number, ignoreCase = true) and item.color.contains(color, ignoreCase = true)
}
