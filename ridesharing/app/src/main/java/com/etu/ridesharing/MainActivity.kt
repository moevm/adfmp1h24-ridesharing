package com.etu.ridesharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.etu.ridesharing.ui.theme.RidesharingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RidesharingTheme {
                RidesharingApp()
            }
        }
    }
}
