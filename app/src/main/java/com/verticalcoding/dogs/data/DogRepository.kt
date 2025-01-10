package com.verticalcoding.dogs.data

import com.verticalcoding.dogs.data.local.database.DogEntity
import com.verticalcoding.dogs.data.local.database.DogEntityDao
import com.verticalcoding.dogs.data.models.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface DogRepository {
    val dogs: Flow<List<Dog>>

    suspend fun add(name: String)
    suspend fun remove(id: Int)
}

// @Inject here identifies the constructor as a dependency,
class DefaultDogRepository @Inject constructor(
    private val dogDao: DogEntityDao
): DogRepository {

    override val dogs: Flow<List<Dog>> = dogDao.getSortedDogs().map { items -> items.map { Dog(id = it.uid, name = it.name, breed = "Jack Russel Terrier") } }

    override suspend fun add(name: String) {
       dogDao.insertDog(DogEntity(name = name))
    }

    override suspend fun remove(id: Int) {
        dogDao.removeDog(id)
    }
}