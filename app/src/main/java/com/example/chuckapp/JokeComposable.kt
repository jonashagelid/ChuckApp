package com.example.chuckapp


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun JokeScreen(navController: NavController, viewModel: JokesViewModel) {
    val currentJoke = viewModel.currentJoke
    val categories = viewModel.categories
    val selectedCategory = viewModel.selectedCategory
    val isCategoryListVisible = viewModel.isCategoryListVisible

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("jokesList") }) {
            Text("View Saved Jokes")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = currentJoke ?: "Joke goes here", modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.fetchRandomJoke() }) {
            Text("Get Random Joke")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.fetchJokeByCategory() }, enabled = selectedCategory != null) {
            Text("Get Joke by Category")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.saveCurrentJoke() }) {
            Text("Save Joke")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Select Category: ${selectedCategory ?: "None"}",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.isCategoryListVisible = !viewModel.isCategoryListVisible }
                .padding(16.dp)
        )

        if (isCategoryListVisible) {
            LazyColumn {
                items(categories) { category ->
                    Text(
                        text = category,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.selectedCategory = category
                                viewModel.isCategoryListVisible = false
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}