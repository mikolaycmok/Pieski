package com.verticalcoding.dogs.data

import com.verticalcoding.dogs.data.local.database.DogEntity
import com.verticalcoding.dogs.data.local.database.DogEntityDao
import com.verticalcoding.dogs.data.models.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

interface DogRepository {
    val dogs: Flow<List<Dog>>

    suspend fun add(name: String)
    suspend fun remove(id: Int)
    suspend fun triggerFav(id: Int)
}

// @Inject here identifies the constructor as a dependency,
class DefaultDogRepository @Inject constructor(
    private val dogDao: DogEntityDao
) : DogRepository {

    override val dogs: Flow<List<Dog>> = dogDao.getSortedDogs().map { items ->
        items.map {
            Dog(
                id = it.uid,
                name = it.name,
                breed = "Jack Russel Terrier",
                isFav = it.isFav
            )
        }
    }

    override suspend fun add(name: String) {
        dogDao.insertDog(DogEntity(name = name, isFav = false))
    }

    override suspend fun remove(id: Int) {
        dogDao.removeDog(id)
    }

    override suspend fun triggerFav(id: Int) {
        dogDao.triggerFavDog(id)
    }
}