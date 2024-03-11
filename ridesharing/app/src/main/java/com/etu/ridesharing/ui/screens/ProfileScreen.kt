package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.navigation.NavController
import com.etu.ridesharing.R
import com.etu.ridesharing.data.CarInfoState
import com.etu.ridesharing.data.UserState
import com.etu.ridesharing.models.CarInfoModel
import com.etu.ridesharing.ui.components.CarDialog
import com.etu.ridesharing.ui.components.ProfileCarCard

@Composable
fun ProfileScreen(
    user: UserState,
    editProfileClick: () -> Unit,
    //onRemoveCar: (CarInfoState) -> Unit, // Функция для удаления автомобиля
    //openDialog: Boolean = false,
    modifier: Modifier = Modifier
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val carState = CarInfoState()
    val carsState = remember { mutableStateOf(user.cars.toMutableList()) }
    when {
        // ...

        openAlertDialog.value -> {
            CarDialog(
                carState = carState,
                actionFunction = {color,brand,number ->
                    carState.color = color
                    carState.mark = brand
                    carState.number = number
                      user.cars.add(carState)
                    user.cars = carsState.value.toMutableList().apply { add(carState) }
                    carsState.value = carsState.value.toMutableList().apply { add(carState) }
                },
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
            value = "${user.name} ${user.patronymic} ${user.surname}",
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        TextField(
            value = "Тел.: ${user.phoneNumber}",
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

            val removeCar: (CarInfoState) -> Unit = { car ->
                carsState.value = carsState.value.toMutableList().apply { remove(car) }
                user.cars = carsState.value.toMutableList().apply { remove(car) }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(carsState.value) { index, car ->
                    if (index !=  0) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    ProfileCarCard(
                        carInfoModel = CarInfoModel(car),
                        modifier = modifier,
                        onEditItem = {},
                        onDeleteItem = {
                            removeCar(car)
                        },
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
