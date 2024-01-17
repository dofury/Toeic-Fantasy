package com.example.toeicfantasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect

import com.example.toeicfantasy.compose.ToeicFantasyApp
import com.example.toeicfantasy.data.CardDatabase
import com.example.toeicfantasy.data.WordCard
import com.example.toeicfantasy.ui.theme.ToeicFantasyTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToeicFantasyTheme {
                ToeicFantasyApp()

                val card = WordCard(1,"resume","이력서")
                val db = CardDatabase.getInstance(applicationContext)



            }
        }
    }

}




