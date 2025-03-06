package com.example.pieski

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pieski.ui.DogListScreen
import com.example.pieski.ui.theme.PieskiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PieskiTheme {
                DogListScreen()
            }
        }
    }
}
