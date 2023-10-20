package com.example.chuckapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.chuckapp.network.ApiService

class MainActivity : ComponentActivity() {
    private val apiService = ApiService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeScreen(apiService)
        }
    }
}
