package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etu.ridesharing.R
import com.etu.ridesharing.data.CarInfoState
import com.etu.ridesharing.models.CarInfoModel
import com.etu.ridesharing.ui.components.CarDialog
import com.etu.ridesharing.ui.components.MyDriveDialog
import com.etu.ridesharing.ui.components.ProfileCarCard

@Composable
fun ProfileScreen(
    editProfileClick: () -> Unit,
    myCarsList: MutableList<CarInfoState>, // Список автомобилей
    onRemoveCar: (CarInfoState) -> Unit, // Функция для удаления автомобиля
    //openDialog: Boolean = false,
    modifier: Modifier = Modifier
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    when {
        // ...
        openAlertDialog.value -> {
            CarDialog(
                onDismissRequest = { openAlertDialog.value = false },
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier.size(96.dp)
            )
        }
        TextField(
            value = "Гость",
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        TextField(
            value = "Тел.:88005553535",
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )

        // Отображение списка карточек автомобилей в Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(myCarsList.size) { carIndex ->
                    if (carIndex > 0) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    ProfileCarCard(
                        carInfoState = myCarsList[carIndex], // Передача состояния автомобиля
                        modifier = modifier,
                        onEditItem = {},
                        onDeleteItem = { onRemoveCar(myCarsList[carIndex]) }, // Передача функции удаления автомобиля
                    )
                }
            }
        }

        // Кнопка для редактирования профиля
        Button(
            onClick = editProfileClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Редактировать профиль", fontSize = 18.sp)
        }
        Button(
            onClick = {openAlertDialog.value = true},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Добавить автомобиль", fontSize = 18.sp)
        }
    }
}
