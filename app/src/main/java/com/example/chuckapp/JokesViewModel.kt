package com.example.chuckapp


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuckapp.db.JokeDao
import com.example.chuckapp.db.JokeEntity
import com.example.chuckapp.network.ApiService
import kotlinx.coroutines.launch



class JokesViewModel(private val apiService: ApiService, private val jokeDao: JokeDao) : ViewModel() {

    var currentJoke by mutableStateOf<String?>(null)
    var savedJokes by mutableStateOf<List<JokeEntity>>(emptyList())
    var categories by mutableStateOf<List<String>>(emptyList())
    var selectedCategory by mutableStateOf<String?>(null)
    var isCategoryListVisible by mutableStateOf(false)

    init {
        fetchCategories()
        loadSavedJokes()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            categories = apiService.getCategories()
        }
    }

    fun loadSavedJokes() {
        viewModelScope.launch {
            savedJokes = jokeDao.getAllJokes()
        }
    }

    fun saveCurrentJoke() {
        currentJoke?.let { joke ->
            viewModelScope.launch {
                jokeDao.insert(JokeEntity(joke = joke))
                loadSavedJokes()
            }
        }
    }

    fun fetchRandomJoke() {
        viewModelScope.launch {
            val response = apiService.getRandomJoke()
            currentJoke = response.value
        }
    }

    fun fetchJokeByCategory() {
        selectedCategory?.let { category ->
            viewModelScope.launch {
                val response = apiService.getJokeByCategory(category)
                currentJoke = response.value
            }
        }
    }
}
