package com.etu.ridesharing.ui.screens

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
import androidx.compose.ui.window.Dialog
import com.etu.ridesharing.R
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
            onDismissRequest = { isSheetOpen = false },
        )
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
}


@Composable
fun CarFilterDialog(
    onDismissRequest: () -> Unit,
) {
    var textMark by rememberSaveable { mutableStateOf("") }
    var textNumber by rememberSaveable { mutableStateOf("") }
    var textColor by rememberSaveable { mutableStateOf("") }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(){
                Column(
                    modifier = Modifier.weight(0.7f).padding(start = 16.dp, top = 32.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .padding(25.dp, 10.dp)
                            .fillMaxWidth(),
                        onClick = { onDismissRequest()  }
                    ){Text("Применить фильтры")}
                }
                Column(modifier = Modifier){
                    Row(modifier = Modifier.padding(25.dp, 10.dp)){
                        AutoCompleteTextField(
                            label = stringResource(id = R.string.mark),
                            categories = listOf("Toyota","Volkswagen","Ford","Hyundai","Honda")
                        )
                    }
                    Row(modifier = Modifier.padding(25.dp, 10.dp)){
                        AutoCompleteTextField(
                            label = stringResource(id = R.string.number),
                            categories = listOf("А001АА","В002ВВ","Е003ЕЕ","К004КК","М005ММ")
                        )
                    }
                    Row(modifier = Modifier.padding(25.dp, 10.dp)){
                        AutoCompleteTextField(
                            label = stringResource(id = R.string.color),
                            categories = listOf("Белый","Чёрный","Серый","Красный","Синий")
                        )
                    }
                }
            }
        }
    }
}
