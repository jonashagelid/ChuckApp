package com.example.chuckapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chuckapp.network.ApiService
import kotlinx.coroutines.launch


@Composable
fun JokeScreen(apiService: ApiService) {
    val coroutineScope = rememberCoroutineScope()
    var joke by remember { mutableStateOf<String?>(null) }
    var categories by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var isCategoryListVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        categories = apiService.getCategories()
        selectedCategory = null
        Log.v("cat", categories.toString())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(150.dp)
                .background(color = Color.Gray.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = joke ?: "Jokebox!")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    val response = apiService.getRandomJoke()
                    joke = response.value
                }
            }) {
                Text(text = "Random Joke")
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (selectedCategory != null) {
                            val response = apiService.getJokeByCategory(selectedCategory!!)
                            joke = response.value
                        }
                    }
                },
                enabled = selectedCategory != null
            ) {
                Text(text = "Joke by Category")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Select Category${if (selectedCategory != null) ": $selectedCategory" else ""}",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray.copy(alpha = 0.2f))
                .clickable(onClick = {
                    isCategoryListVisible = !isCategoryListVisible
                })
                .padding(16.dp)
        )
        if (isCategoryListVisible) {
            LazyColumn(
                modifier = Modifier.height(500.dp)
            ) {
                items(categories) { category ->
                    Text(
                        text = category,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = {
                                selectedCategory = category
                                isCategoryListVisible = false
                            })
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}