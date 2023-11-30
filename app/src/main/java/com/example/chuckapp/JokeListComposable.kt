package com.example.chuckapp


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chuckapp.db.JokeDao
import com.example.chuckapp.db.JokeEntity

@Composable
fun JokesListScreen(jokeDao: JokeDao, navController: NavController) {
    var savedJokes by remember { mutableStateOf<List<JokeEntity>>(emptyList()) }

    LaunchedEffect(Unit) {
        savedJokes = jokeDao.getAllJokes()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("jokeScreen") }) {
            Text("Back")
        }
        LazyColumn {

            items(savedJokes) { jokeEntity ->
                Text(text = jokeEntity.joke)
            }
        }

    }

}
