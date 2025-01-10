package com.verticalcoding.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// This is a model class for the database
@Entity
data class Dog(
    val name: String
) {
    @PrimaryKey(autoGenerate=true)
    var uid: Int = 0
}

// Dao basically does all the operations on the database for given model
@Dao
interface DogDao {

    @Query("SELECT * FROM dog")
    fun getAllDogs(): Flow<List<Dog>>

    @Query("SELECT * FROM dog ORDER BY uid DESC LIMIT 10")
    fun getSortedDogs(): Flow<List<Dog>>

    @Insert
    suspend fun insertDog(dog: Dog)
}