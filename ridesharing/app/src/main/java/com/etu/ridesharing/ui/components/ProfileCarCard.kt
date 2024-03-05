package com.etu.ridesharing.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.etu.ridesharing.R
import com.etu.ridesharing.data.CarInfoState

@Composable
fun ProfileCarCard(
    carInfoState: CarInfoState, // Состояние информации об автомобиле
    onDeleteItem: () -> Unit, // Функция для удаления автомобиля
    onEditItem: () -> Unit, // Функция для редактирования автомобиля
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
    Card(modifier = modifier) {
        Row(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Column {
                Text(text = stringResource(id = R.string.mark, carInfoState.mark))
                Text(text = stringResource(id = R.string.number, carInfoState.number))
                Text(text = stringResource(id = R.string.color, carInfoState.color))
            }
            Column {
                IconButton(onClick = onDeleteItem) {
                    Icon(Icons.Outlined.Close, contentDescription = "Удалить")
                }
                IconButton(onClick = { openAlertDialog.value = true }) {
                    Icon(Icons.Outlined.Edit, contentDescription = "Редактировать")
                }
            }
        }
    }
}

@Composable
fun CarDialog(
    onDismissRequest: () -> Unit,
) {
    var brand by rememberSaveable { mutableStateOf("") }
    var number by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableStateOf("") }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                CustomTextField(
                    text = "Марка:",
                    type = "text",
                    label = { Text("Марка автомобиля") },
                    value = brand,
                    onValueChange = {
                        brand = it
                    },
                )
                CustomTextField(
                    text = "Номер:",
                    type = "text",
                    label = { Text("Номер автомобиля") },
                    value = number,
                    onValueChange = {
                        number = it
                    },
                )
                CustomTextField(
                    text = "Цвет: ",
                    type = "text",
                    label = { Text("Цвет автомобиля") },
                    value = color,
                    onValueChange = {
                        color = it
                    },
                )
                Button(onClick = {  }, modifier = Modifier.padding(top = 16.dp)) {
                    Text("Сохранить")
                }
            }
        }
    }
}
