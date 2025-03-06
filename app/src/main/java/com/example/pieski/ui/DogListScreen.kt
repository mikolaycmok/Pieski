package com.example.pieski.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pieski.model.Dog
import com.example.pieski.viewmodel.DogViewModel

@Composable
fun DogListScreen(
    viewModel: DogViewModel = viewModel()
) {
    val dogs = viewModel.dogs
    val dogNameInput = viewModel.dogNameInput
    val isDuplicateName = viewModel.isDuplicateName

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Pole tekstowe
        TextField(
            value = dogNameInput,
            onValueChange = { viewModel.onDogNameInputChanged(it) },
            label = { Text("Wpisz imię pieska") },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = if (isDuplicateName) Color.Red else Color.Gray
                )
        )

        // Przyciski "Dodaj" i "Szukaj"
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.addDog() },
                enabled = dogNameInput.isNotEmpty() && !isDuplicateName
            ) {
                Text("Dodaj")
            }
            Button(
                onClick = { viewModel.searchDogs() },
                enabled = dogNameInput.isNotEmpty()
            ) {
                Text("Szukaj")
            }
        }

        // Komunikat o duplikacie
        if (isDuplicateName) {
            Text(
                text = "Taki piesek już jest na liście!",
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Lista psów
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(dogs) { dog ->
                DogRow(
                    dog = dog,
                    onToggleFavorite = { viewModel.toggleFavorite(dog) }
                )
            }
        }
    }
}

@Composable
fun DogRow(
    dog: Dog,
    onToggleFavorite: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = dog.name)
        IconButton(onClick = onToggleFavorite) {
            Icon(
                imageVector = if (dog.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null
            )
        }
    }
}
