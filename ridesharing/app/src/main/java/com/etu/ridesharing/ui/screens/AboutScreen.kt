package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etu.ridesharing.R
import com.etu.ridesharing.data.DataCitiesList
import com.etu.ridesharing.models.DriveInfoModel
import com.etu.ridesharing.ui.components.AutoCompleteTextField
import com.etu.ridesharing.ui.components.FindCompanionCard

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxHeight()) {
        Column {
            Text(fontSize = 26.sp,text = "Приложение разрабатывали студенты СПбГЭТУ <<ЛЭТИ>>")
            Divider(
                color = Color.Green,
                thickness = 4.dp
            )
            Text(fontSize = 26.sp,text = "Пичугин Максим Владиславович")
            Text(fontSize = 26.sp,text = "Середенков Антон Александрович")
            Text(fontSize = 26.sp,text = "Сологуб Николай Алексеевич")
            Divider(
                color = Color.Green,
                thickness = 4.dp
            )
            Text(fontSize = 26.sp,text = "группа: 0303, ФКТИ, кафедра МО ЭВМ")
        }
    }

}