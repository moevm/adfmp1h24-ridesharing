package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etu.ridesharing.R
import com.etu.ridesharing.data.UserState


@Composable
fun RegistrationScreen(
    isErrorAuthorization: Boolean,
    onButtonClick: (String) -> Unit,
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
            isError = isErrorAuthorization,
            supportingText = {
                if (isErrorAuthorization) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Пользователя с таким телефоном нет",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            value = textFieldValue,
            onValueChange = {
                    textFieldValue = TextFieldValue(it.text.take(11))
                        .copy(selection = TextRange(formatPhoneNumber(it.text.take(11)).length))
            },
            visualTransformation = PhoneTransformation(),
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
            onClick = {
                onButtonClick(textFieldValue.text)
                      },
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

class PhoneTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return phoneFilter(text)
    }
}

fun phoneFilter(text: AnnotatedString): TransformedText {
    val trimmed = text.text.take(11) // Ограничиваем вводимый текст длиной "+X-XXX-XXX-XX-XX"
    var out = ""
    for (i in trimmed.indices) {
        if(i == 0){
            out = "+"
        }
        out += trimmed[i]

        if (i == 0 || i == 3 || i == 6 || i == 8) out += "-" // Добавляем "-" после ввода соответствующих символов
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return if (offset == 0) offset
            else if (offset == 1) offset + 1
            else if (offset <= 4) offset + 2
            else if (offset <= 7) offset + 3
            else if (offset <= 9) offset + 4
            else offset + 5 // Возвращаем конечное положение, равное длине исходной строки
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when {
                offset <= 3 -> 0
                offset <= 7 -> 0
                offset <= 11 -> 0
                offset <= trimmed.length -> 0
                else -> 0 // Корректируем, чтобы не выходить за границы исходной строки
            }
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}