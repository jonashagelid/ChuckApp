package com.example.chuckapp


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun JokesListScreen(navController: NavController, viewModel: JokesViewModel) {
    val savedJokes = viewModel.savedJokes

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),/**/
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigateUp() }) {
            Text("Back")
        }

        LazyColumn {
            items(savedJokes) { jokeEntity ->
                Text(text = jokeEntity.joke)
                Divider()
            }
        }
    }
}
