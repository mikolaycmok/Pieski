package com.example.pieski.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.pieski.model.Dog

class DogViewModel : ViewModel() {

    // Główna lista psów
    private val _dogs = mutableStateListOf<Dog>()
    val dogs: List<Dog> = _dogs

    // Zestaw istniejących nazw (do sprawdzania duplikatów)
    private val dogNames = mutableSetOf<String>()

    // Aktualny tekst wpisany w TextField
    var dogNameInput by mutableStateOf("")
        private set

    // Flaga, czy wpisana nazwa już istnieje
    var isDuplicateName by mutableStateOf(false)
        private set

    fun onDogNameInputChanged(newName: String) {
        dogNameInput = newName
        isDuplicateName = dogNames.contains(newName.trim())
    }

    fun addDog() {
        val name = dogNameInput.trim()
        if (name.isNotEmpty() && !dogNames.contains(name)) {
            val newDog = Dog(name = name)
            _dogs.add(newDog)
            dogNames.add(name)
        }
        dogNameInput = ""
        isDuplicateName = false
    }

    fun searchDogs() {
        // Na potrzeby przykładu – tylko czyścimy pole tekstowe
        dogNameInput = ""
    }

    fun toggleFavorite(dog: Dog) {
        val index = _dogs.indexOf(dog)
        if (index != -1) {
            val updatedDog = dog.copy(isFavorite = !dog.isFavorite)
            _dogs[index] = updatedDog
            reorderDogs()
        }
    }

    private fun reorderDogs() {
        val favorites = _dogs.filter { it.isFavorite }
        val nonFavorites = _dogs.filter { !it.isFavorite }
        _dogs.clear()
        _dogs.addAll(favorites + nonFavorites)
    }
}
