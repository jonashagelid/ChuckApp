package com.example.chuckapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chuckapp.db.JokeDatabase
import com.example.chuckapp.network.ApiService

class
MainActivity : ComponentActivity() {
    private val apiService = ApiService.create()
    private lateinit var db: JokeDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = JokeDatabase.getDatabase(this)
        val viewModel: JokesViewModel by viewModels {
            JokesViewModelFactory(apiService, db.jokeDao())
        }
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "jokeScreen") {
                composable("jokeScreen") { JokeScreen(navController, viewModel) }
                composable("jokesList") { JokesListScreen(navController, viewModel) }
            }
        }
    }
}
