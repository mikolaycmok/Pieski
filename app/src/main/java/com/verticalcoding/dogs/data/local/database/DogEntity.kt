package com.verticalcoding.dogs.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// This is a model class for the database
@Entity(tableName = "dogs")
data class DogEntity(
    val name: String
) {
    @PrimaryKey(autoGenerate=true)
    var uid: Int = 0
}

// Dao basically does all the operations on the database for given model
@Dao
interface DogEntityDao {

    @Query("SELECT * FROM dogs")
    fun getAllDogs(): Flow<List<DogEntity>>

    @Query("SELECT * FROM dogs ORDER BY uid DESC LIMIT 10")
    fun getSortedDogs(): Flow<List<DogEntity>>

    @Insert
    suspend fun insertDog(dog: DogEntity)
}