package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.etu.ridesharing.data.UserState

@Composable
fun EditProfileScreen(
    userState: UserState,
    onBackStrack: () -> Unit,
) {
    var firstNameState by rememberSaveable { mutableStateOf(userState.name) }
    var lastNameState by rememberSaveable { mutableStateOf(userState.surname) }
    var middleNameState by rememberSaveable{ mutableStateOf(userState.patronymic) }
    var phoneNumberState by rememberSaveable { mutableStateOf(userState.phoneNumber) }
    var isErrorPhone by rememberSaveable { mutableStateOf(false) }
    var isErrorName by rememberSaveable { mutableStateOf(false) }
    var isErrorSurname by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    fun validatePhone(phone: String){
            isErrorPhone = phone.length < 11
    }
    fun validateName(name: String){
        isErrorName = name.isEmpty()
    }
    fun validateSurname(surname: String){
        isErrorSurname = surname.isEmpty()
    }
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
            value = firstNameState,
            isError = isErrorName,
            supportingText = {
                if (isErrorName) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Имя должно быть заполнено",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            onValueChange = {
                firstNameState = it
                validateName(firstNameState)
                            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Имя*") },
        )
        TextField(
            value = lastNameState,
            isError = isErrorSurname,
            supportingText = {
                if (isErrorSurname) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Фамилия должна быть заполнена",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            onValueChange = {
                lastNameState = it
                validateSurname(lastNameState)
                            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Фамилия*") }
        )
        TextField(
            value = middleNameState,
            onValueChange = {
                middleNameState = it
                validateName(middleNameState)
                            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Отчество") }
        )
        TextField(
            value = phoneNumberState,
            isError = isErrorPhone,
            supportingText = {
                if (isErrorPhone) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "телефон введён некорректно",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            onValueChange = {

                // Фильтруем ввод, оставляя только числа
                val newText = it.filter { char -> char.isDigit() }
                validatePhone(newText)
                if (newText.length <= 11) {
                    phoneNumberState = formatPhoneNumber(newText)
                }
            },
            label = { Text(text = "Телефон*") },
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions {  },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        // Кнопки
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = {
                    if(!isErrorName && !isErrorSurname && !isErrorPhone){
                        userState.name = firstNameState
                        userState.surname = lastNameState
                        userState.patronymic = middleNameState
                        userState.phoneNumber = phoneNumberState
                        onBackStrack()
                    }

                          },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(text = "Сохранить изменения", fontSize = 18.sp)
            }
        }
    }
}

private fun formatPhoneNumber(phoneNumber: String): String {
    val formattedNumber = StringBuilder()
    if (phoneNumber.isNotEmpty()) {
        formattedNumber.append("+")
        phoneNumber.chunked(1).forEachIndexed { index, char ->
            formattedNumber.append(char)
            if (index == 0) {
                formattedNumber.append("-")
            } else if (index == 3 || index == 6 || index == 8) {
                formattedNumber.append("-")
            }

        }
    }
    return formattedNumber.toString()
}
