package com.verticalcoding.dogs.data

import com.verticalcoding.dogs.data.models.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface DogRepository {
    val dogs: Flow<List<Dog>>

    suspend fun add(name: String)
}

// @Inject here identifies the constructor as a dependency,
class DefaultDogRepository @Inject constructor(
    private val dogDao: com.verticalcoding.dogs.data.local.database.DogEntityDao
): DogRepository {

    override val dogs: Flow<List<Dog>> = dogDao.getSortedDogs().map { items -> items.map { Dog(id = it.uid, name = it.name) } }

    override suspend fun add(name: String) {
       dogDao.insertDog(com.verticalcoding.dogs.data.local.database.DogEntity(name = name))
    }
}