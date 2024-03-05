package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.etu.ridesharing.R

@Composable
fun EditProfileScreen(navController: NavController) {
    val firstNameState = remember { mutableStateOf("") }
    val lastNameState = remember { mutableStateOf("") }
    val middleNameState = remember { mutableStateOf("") }
    val telephon = remember { mutableStateOf("88005553535") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            },
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier.size(96.dp)
            )
        }
        // Поля для редактирования ФИО
        TextField(
            value = firstNameState.value,
            onValueChange = { firstNameState.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Имя") },
        )
        TextField(
            value = lastNameState.value,
            onValueChange = { lastNameState.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Фамилия") }
        )
        TextField(
            value = middleNameState.value,
            onValueChange = { middleNameState.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Отчество") }
        )
        TextField(
            value = telephon.value,
            onValueChange = {
                // Фильтруем ввод, оставляя только числа
                val newText = it.filter { char -> char.isDigit() }
                telephon.value = newText
            },
            label = { Text(text = "Телефон") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        // Кнопки
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(text = "Сохранить изменения", fontSize = 18.sp)
            }
        }
    }
}
