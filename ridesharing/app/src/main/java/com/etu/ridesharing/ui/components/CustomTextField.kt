package com.etu.ridesharing.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    isError: Boolean = false,
    supportingText: @Composable() (() -> Unit) = {Text(text = "error")},
    text: String,
    type: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadIcon: @Composable() (() -> Unit)? = null,
    label: @Composable()() -> Unit,
    modifier: Modifier = Modifier,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column() {
            Text(
                text = text,
            )
        }
        Column(modifier = Modifier.padding(start = 2.dp)) {
        when (type) {
            "date" -> {
                OutlinedTextField(
                    isError = isError,
                    supportingText = supportingText,
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number, // Устанавливаем тип клавиатуры для числового ввода
                        imeAction = ImeAction.Next // Устанавливаем действие клавиатуры для перехода к следующему полю
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        // Здесь можно обработать нажатие кнопки перехода к следующему полю
                    }),
                    singleLine = true,
                    shape = RoundedCornerShape(4.dp), // Форма поля ввода
                    label = label,
                    leadingIcon = leadIcon,
                    visualTransformation = DateTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Blue, // Цвет рамки при активации поля
                        unfocusedBorderColor = Color.Gray, // Цвет рамки при неактивном поле
                    ),
                )
            }

            "time" -> {
                OutlinedTextField(
                    isError = isError,
                    supportingText = supportingText,
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number, // Устанавливаем тип клавиатуры для числового ввода
                        imeAction = ImeAction.Next // Устанавливаем действие клавиатуры для перехода к следующему полю
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        // Здесь можно обработать нажатие кнопки перехода к следующему полю
                    }),
                    singleLine = true,
                    shape = RoundedCornerShape(4.dp), // Форма поля ввода
                    label = label,
                    leadingIcon = leadIcon,
                    visualTransformation = TimeTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Blue, // Цвет рамки при активации поля
                        unfocusedBorderColor = Color.Gray, // Цвет рамки при неактивном поле
                    ),
                )
            }

            "text" -> {
                OutlinedTextField(
                    isError = isError,
                    supportingText = supportingText,
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text, // Устанавливаем тип клавиатуры для числового ввода
                        imeAction = ImeAction.Next // Устанавливаем действие клавиатуры для перехода к следующему полю
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        // Здесь можно обработать нажатие кнопки перехода к следующему полю
                    }),
                    singleLine = true,
                    shape = RoundedCornerShape(4.dp), // Форма поля ввода
                    label = label,
                    leadingIcon = leadIcon,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Blue, // Цвет рамки при активации поля
                        unfocusedBorderColor = Color.Gray, // Цвет рамки при неактивном поле
                    ),
                )
            }

            "number" -> {
                OutlinedTextField(
                    isError = isError,
                    supportingText = supportingText,
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number, // Устанавливаем тип клавиатуры для числового ввода
                        imeAction = ImeAction.Next // Устанавливаем действие клавиатуры для перехода к следующему полю
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        // Здесь можно обработать нажатие кнопки перехода к следующему полю
                    }),
                    singleLine = true,
                    shape = RoundedCornerShape(4.dp), // Форма поля ввода
                    label = label,
                    leadingIcon = leadIcon,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Blue, // Цвет рамки при активации поля
                        unfocusedBorderColor = Color.Gray, // Цвет рамки при неактивном поле
                    ),
                )
            }
            "dateTime" -> {
                OutlinedTextField(
                    isError = isError,
                    supportingText = supportingText,
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number, // Устанавливаем тип клавиатуры для числового ввода
                        imeAction = ImeAction.Next // Устанавливаем действие клавиатуры для перехода к следующему полю
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        // Здесь можно обработать нажатие кнопки перехода к следующему полю
                    }),
                    singleLine = true,
                    shape = RoundedCornerShape(4.dp), // Форма поля ввода
                    label = label,
                    leadingIcon = leadIcon,
                    //visualTransformation = DateTimeTransformation(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Blue, // Цвет рамки при активации поля
                        unfocusedBorderColor = Color.Gray, // Цвет рамки при неактивном поле
                    ),
                )
            }
        }
    }
    }
}



class DateTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }
}

fun dateFilter(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 2 == 1 && i < 4) out += "/"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset +1
            if (offset <= 8) return offset +2
            return 10
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <=2) return offset
            if (offset <=5) return offset -1
            if (offset <=10) return offset -2
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}

class TimeTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return timeFilter(text)
    }
}

fun timeFilter(text: AnnotatedString): TransformedText {
    val trimmed = text.text.take(5) // Ограничиваем вводимый текст длиной "чч:мм"
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i == 1) out += ":" // Добавляем ":" после ввода двух символов для часов
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            return offset + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            return offset - 1
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}



