package com.example.toeicfantasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.example.toeicfantasy.compose.ToeicFantasyApp
import com.example.toeicfantasy.ui.theme.ToeicFantasyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToeicFantasyTheme {
                ToeicFantasyApp()
            }
        }
    }

}




