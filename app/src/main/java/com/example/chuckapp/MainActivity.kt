package com.example.chuckapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chuckapp.db.JokeDatabase
import com.example.chuckapp.network.ApiService

class MainActivity : ComponentActivity() {
    private val apiService = ApiService.create()
    private lateinit var db: JokeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = JokeDatabase.getDatabase(this)
        setContent {
            val navController = rememberNavController()

            // Set up NavHost
            NavHost(navController = navController, startDestination = "jokeScreen") {
                composable("jokeScreen") { JokeScreen(apiService, db.jokeDao(), navController) }
                composable("jokesList") { JokesListScreen(db.jokeDao(), navController) }
            }
        }
    }
}
