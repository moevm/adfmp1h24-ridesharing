package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.ui.components.CustomTextField

@Composable @Preview
fun AdminPageStats() {
    var textFrom by rememberSaveable { mutableStateOf("") }
    var textTo by rememberSaveable { mutableStateOf("") }
    val maxCharDate = 8

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(){
            CustomTextField(
                text = "C:",
                type = "date",
                label = { Text("дд/мм/гггг") },
                value = textFrom,
                onValueChange = {
                    if (it.length <= maxCharDate) textFrom = it
                },
                leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(){
            CustomTextField(
                text = "По:",
                type = "date",
                label = { Text("дд/мм/гггг") },
                value = textTo,
                onValueChange = {
                    if (it.length <= maxCharDate) textTo = it
                },
                leadIcon = { Icon(Icons.Outlined.DateRange, contentDescription = "Localized description") }
            )
        }

        Column(Modifier.fillMaxHeight().padding(top=25.dp)) {
            Row {
                Text(text = "Пользователей", Modifier.fillMaxWidth(0.3F).padding(top=25.dp))
                Text(text = "4", Modifier.fillMaxWidth(0.5F).padding(top=25.dp, start=25.dp))
            }
            Row {
                Text(text = "Всего поездок", Modifier.fillMaxWidth(0.3F).padding(top=25.dp))
                Text(text = "1", Modifier.fillMaxWidth(0.5F).padding(top=25.dp, start=25.dp))
            }
            Row {
                Text(text = "Общий пробег", Modifier.fillMaxWidth(0.3F).padding(top=25.dp))
                Text(text = "10", Modifier.fillMaxWidth(0.5F).padding(top=25.dp, start=25.dp))
            }
        }

    }
}