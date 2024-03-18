package com.etu.ridesharing.ui.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.etu.ridesharing.R
import com.etu.ridesharing.data.DataCitiesList
import com.etu.ridesharing.data.DriveInfoState
import com.etu.ridesharing.data.UserState
import com.etu.ridesharing.models.DriveInfoModel
import com.etu.ridesharing.ui.components.AutoCompleteTextField
import com.etu.ridesharing.ui.components.CustomTextField
import com.etu.ridesharing.ui.components.FindCompanionCard
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.UUID

@Composable
fun FindCompanionScreen(
    usersList: MutableList<UserState>,
    currentUser: UserState,
    companionDrivesList: MutableList<DriveInfoState>,
    onItemClick: (Int, UUID?) -> Unit,
    modifier: Modifier = Modifier
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    var filterDateFrom by rememberSaveable { mutableStateOf("") }
    var filterDateTo by rememberSaveable { mutableStateOf("") }
    var filterPriceLow by rememberSaveable { mutableStateOf("") }
    var filterPriceHigh by rememberSaveable { mutableStateOf("") }
    var filterSearchFrom by rememberSaveable { mutableStateOf("") }
    var filterSearchTo by rememberSaveable { mutableStateOf("") }
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
            LazyColumn(modifier = Modifier.padding(top = 32.dp)) {
                var filtUsers = usersList.filter {
                    filterUser(
                        it,
                        currentUser
                    )
                }
                filtUsers.forEach{user ->
                    var filtered = user.userDrives.filter {
                        filterFun(
                            it,
                            filterDateFrom,
                            filterDateTo,
                            filterPriceLow,
                            filterPriceHigh,
                            filterSearchFrom,
                            filterSearchTo
                        )
                    }
                    items(filtered.size) { driveIndex ->
                        if (driveIndex > 0) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        FindCompanionCard(
                            user = user,
                            onItemClick = onItemClick,
                            driveInfoModel = DriveInfoModel( filtered[driveIndex]),
                            modifier = modifier
                                .size(width = 350.dp, height = 150.dp),
                        )
                    }

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
    var isErrorDataF by rememberSaveable { mutableStateOf(false) }
    var isErrorDataT by rememberSaveable { mutableStateOf(false) }
    var isErrorLow by rememberSaveable { mutableStateOf(false) }
    var isErrorHight by rememberSaveable { mutableStateOf(false) }

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    val DatePickerDialog1 = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            filterDateFrom_temp = "${mDayOfMonth.toString().padStart(2,'0')}${(mMonth+1).toString().padStart(2,'0')}$mYear"
        }, mYear, mMonth, mDay
    )

    val DatePickerDialog2 = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            filterDateTo_temp = "${mDayOfMonth.toString().padStart(2,'0')}${(mMonth+1).toString().padStart(2,'0')}$mYear"
        }, mYear, mMonth, mDay
    )

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(
            ){
                Column(
                    modifier = Modifier.weight(0.9f).padding(start = 8.dp, top = 32.dp),
                ) {
                    val maxCharDate = 8
                    Text(text = "Дата:")

                    CustomTextField(
                        isError = isErrorDataF,
                        supportingText = {
                            if (isErrorDataF){
                                Text(
                                    text = "Неправильная дата",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        text = "от:",
                        type = "date",
                        label = { Text("дд/мм/гггг") },
                        value = filterDateFrom_temp,
                        onValueChange = {
                            if (it.length <= maxCharDate){
                                filterDateFrom_temp = it}
                            if(filterDateFrom_temp.length < 8 && filterDateFrom_temp.length>0){
                                isErrorDataF = true
                            }else{
                                isErrorDataF = filterDateFrom_temp.length == 8 && !checkValue(filterDateFrom_temp)
                            }
                            isErrorDataF = filterDateFrom_temp.length == 8 && !checkValue(filterDateFrom_temp)
                        },
                        leadIcon = { IconButton(onClick = { DatePickerDialog1.show() }) {
                            Icon(Icons.Filled.DateRange, contentDescription = "Localized description")
                        } }
                    )

                    CustomTextField(
                        isError = isErrorDataT,
                        supportingText = {
                            if (isErrorDataT){
                                Text(
                                    text = "Неправильная дата",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        text = "до:",
                        type = "date",
                        label = { Text("дд/мм/гггг") },
                        value = filterDateTo_temp,
                        onValueChange = {
                            if (it.length <= maxCharDate){
                                filterDateTo_temp = it
                            }
                            isErrorDataT = filterDateTo_temp.length == 8 && !checkValue(filterDateTo_temp)
                        },
                        leadIcon = { IconButton(onClick = { DatePickerDialog2.show() }) {
                            Icon(Icons.Filled.DateRange, contentDescription = "Localized description")
                        } }
                    )

                    Text(text = "Цена:")
                    CustomTextField(
                        isError = isErrorLow,
                        supportingText = {
                            if (isErrorLow){
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Отрицательное число",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        text = "От:",
                        type = "number",
                        label = { Text("тенге") },
                        value = filterPriceLow_temp,
                        onValueChange = {
                            filterPriceLow_temp = it
                            isErrorLow = filterPriceLow_temp.toIntOrNull() ?: 0 <0
                        },
                    )
                    CustomTextField(
                        isError = isErrorHight,
                        supportingText = {
                            if (isErrorHight){
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Отрицательное число",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        text = "До:",
                        type = "number",
                        label = { Text("тенге") },
                        value = filterPriceHight_temp,
                        onValueChange = {
                            filterPriceHight_temp = it
                            isErrorHight = filterPriceHight_temp.toIntOrNull() ?: 0 <0
                        },
                    )
                    Button(onClick = {
                        if(!(filterDateTo_temp.length == 8 && !checkValue(filterDateTo_temp)))
                            isErrorDataT = filterDateTo_temp.length < 8 && filterDateTo_temp.length>0
                        if(!(filterDateFrom_temp.length == 8 && !checkValue(filterDateFrom_temp)))
                            isErrorDataF = filterDateFrom_temp.length < 8 && filterDateFrom_temp.length>0
                        if(!isErrorDataF and !isErrorDataT and !isErrorHight and !isErrorLow){
                            changeValues(filterDateFrom_temp, filterDateTo_temp, filterPriceLow_temp,filterPriceHight_temp)
                            onDismissRequest()
                        } },
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


fun isDateBefore(date1: String, date2: String): Boolean {
    val dateFormat = SimpleDateFormat("ddMMyyyy")
    val parsedDate1 = dateFormat.parse(date1)
    val parsedDate2 = dateFormat.parse(date2)
    return parsedDate1.before(parsedDate2) or (parsedDate1 == parsedDate2)
}

fun filterFun(item : DriveInfoState, from : String = "", to : String = "", priceLow : String = "", priceHight : String = "",cityFrom: String,cityTo: String) : Boolean{
    var flag1 = true
    var flag2 = true
    var flag3 = true
    var flag4 = true
    var flag5 = true
    var flag6 = true
    if(from == "") {
        flag1 = true
    }else{
        flag1 = isDateBefore(from, item.driveDate.replace(".", ""))
    }
    if(to == ""){
        flag2 = true
    }else{
        flag2 = isDateBefore(item.driveDate.replace(".", ""),to)
    }
    if((priceLow == "")or(item.price >= priceLow.toIntOrNull() ?: 0)){
        flag3 = true
    }
    if((priceHight == "")or(item.price <= priceHight.toIntOrNull() ?: 0)){
        flag4 = true
    }
    if(cityFrom == ""){
        flag5 = true
    }else{
        flag5 = item.from.startsWith(cityFrom, ignoreCase = true)
    }
    if(cityTo == ""){
        flag6 = true
    }else{
        flag6 = item.to.startsWith(cityTo, ignoreCase = true)
    }
    return flag1 and flag2 and flag3 and flag4 and flag5 and flag6
}
fun filterUser(item:UserState,user:UserState): Boolean{
    return item != user
}