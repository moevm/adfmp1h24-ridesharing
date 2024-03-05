package com.etu.ridesharing.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etu.ridesharing.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable @Preview
fun MessageCardLeft(
    author: String = "Техническая поддержка",
    text: String = "baobabghjnfgnhkjlfghjfsghjlsjhjdhjgjhgkjdfhgjhdkjghdfslhgkjdfhsjghdjhgjldhjgdsghdjshgl",
    modifier: Modifier = Modifier,
){
    Row(modifier = modifier
            .background(color = colorResource(id = R.color.purple_200))
            .fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Column(){
            Text(text = author, modifier=Modifier.padding(10.dp))
            Text(text = text, modifier=Modifier.padding(start=10.dp, end=10.dp, bottom=10.dp))
        }
    }
}

@Composable @Preview
fun MessageCardRight(
    text: String = "baobab",
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier
            .background(color = colorResource(id = R.color.teal_200))
            .fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ){
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End){
            Text(text = text, modifier=Modifier.padding(10.dp))
        }
    }
}

