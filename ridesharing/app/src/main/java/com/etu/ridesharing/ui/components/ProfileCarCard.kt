package com.etu.ridesharing.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.etu.ridesharing.R
import com.etu.ridesharing.models.CarInfoModel

@Composable
fun ProfileCarCard(
    carInfoModel: CarInfoModel, // Модель информации об автомобиле
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
        val uiState by carInfoModel.uiState.collectAsState()
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column {
                Text(text = stringResource(id = R.string.mark, uiState.mark))
                Text(text = stringResource(id = R.string.number, uiState.number))
                Text(text = stringResource(id = R.string.color, uiState.color))
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.7f)
            ) {
                IconButton(
                    onClick = onDeleteItem,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(Icons.Outlined.Close, contentDescription = "Удалить")
                }
                IconButton(
                    onClick = { openAlertDialog.value = true },
                    modifier = Modifier.size(36.dp)
                ) {
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
            Row {
                Column(
                    modifier = Modifier.weight(0.7f).padding(start = 16.dp, top = 32.dp)
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
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    Button(onClick = { onDismissRequest() }, modifier = Modifier
                        .padding( top = 16.dp, start = 36.dp, bottom = 16.dp)) {
                        Text("Сохранить")
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


