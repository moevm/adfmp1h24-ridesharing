package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etu.ridesharing.R

@Composable
fun ProfileScreen() {
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = "Добавить машину", fontSize = 18.sp)
            }
            Button(
                onClick = {  },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = "Редактировать профиль", fontSize = 18.sp)
            }
        }
    }
}
