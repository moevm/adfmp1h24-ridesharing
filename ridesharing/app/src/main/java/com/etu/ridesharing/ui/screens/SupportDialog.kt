package com.etu.ridesharing.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.R
import com.etu.ridesharing.data.MessagesList
import com.etu.ridesharing.ui.components.MessageCardLeft
import com.etu.ridesharing.ui.components.MessageCardRight

@Composable @Preview
fun SupportDialog(
    messagesList: MessagesList = MessagesList,
    modifier: Modifier = Modifier
){
    val focusManager = LocalFocusManager.current
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(messagesList.MessageList.size) { driveIndex ->
                if (driveIndex > 0) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                if (messagesList.MessageList[driveIndex].author == "Техническая поддержка") {
                    MessageCardLeft(
                        text = messagesList.MessageList[driveIndex].message,
                        author = messagesList.MessageList[driveIndex].author,
                    )
                } else {
                    MessageCardRight(
                        text = messagesList.MessageList[driveIndex].message,
                    )
                }
            }
        }
        Row() {
            val text = remember { mutableStateOf("") }
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                modifier = Modifier.fillMaxWidth(0.9f),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            text.value = ""
                        },
                        content = { Icon(painter = painterResource(R.drawable.baseline_send_24), contentDescription = "", modifier = Modifier.size(size = 46.dp),) }
                    )
                }
            )
        }
    }
}