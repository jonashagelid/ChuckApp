package com.example.chuckapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chuckapp.db.JokeDao
import com.example.chuckapp.network.ApiService

class JokesViewModelFactory(
    private val apiService: ApiService,
    private val jokeDao: JokeDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JokesViewModel(apiService, jokeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
