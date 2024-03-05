package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etu.ridesharing.R


@Composable
fun RegistrationScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Верхняя часть экрана
        Text(
            text = "РАЙДШЕРИНГ",
            fontSize = 24.sp,
            modifier = Modifier.padding(vertical = 50.dp)
        )

        // Средняя часть экрана
        var textFieldValue by remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = textFieldValue,
            onValueChange = {
                // Фильтруем ввод, оставляя только числа
                val newText = it.text.filter { char -> char.isDigit() }
                textFieldValue = TextFieldValue(newText)
                    .copy(selection = TextRange(newText.length))
            },
            label = { Text("Телефон") },
            modifier = Modifier.padding(vertical = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            leadingIcon = {
                // Ваша картинка
                Image(
                    painter = painterResource(id = R.drawable.telephon),
                    contentDescription = "Image",
                    modifier = Modifier.size(16.dp)
                )
            }
        )

        // Нижняя часть экрана
        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .width(300.dp)

        ) {
            Text(
                text = "ВОЙТИ",
                fontSize = 24.sp
            )
        }
    }
}
