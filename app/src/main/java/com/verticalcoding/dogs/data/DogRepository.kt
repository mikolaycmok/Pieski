package com.verticalcoding.dogs.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface DogRepository {
    val dogs: Flow<List<String>>

    suspend fun add(name: String)
}

// @Inject here identifies the constructor as a dependency,
class DefaultDogRepository @Inject constructor(
    private val dogDao: com.verticalcoding.dogs.data.local.database.DogDao
): DogRepository {

    override val dogs: Flow<List<String>> = dogDao.getSortedDogs().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
       dogDao.insertDog(com.verticalcoding.dogs.data.local.database.Dog(name = name))
    }
}