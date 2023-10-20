package com.example.chuckapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.chuckapp.db.JokeDatabase
import com.example.chuckapp.network.ApiService

class MainActivity : ComponentActivity() {
    private val apiService = ApiService.create()
    private lateinit var db: JokeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = JokeDatabase.getDatabase(this)
        setContent {
            JokeScreen(apiService, db.jokeDao())
        }
    }
}
