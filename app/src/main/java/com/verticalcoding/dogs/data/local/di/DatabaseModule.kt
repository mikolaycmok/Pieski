package com.verticalcoding.dogs.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Dagger module is class that will be included in the dependency graphs, build by Dagger
// This Dagger module will be included in singleton components
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDogDao(appDatabase: com.verticalcoding.dogs.data.local.database.AppDatabase): com.verticalcoding.dogs.data.local.database.DogDao {
        return appDatabase.dogDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): com.verticalcoding.dogs.data.local.database.AppDatabase {
       return Room.databaseBuilder(
           appContext,
           com.verticalcoding.dogs.data.local.database.AppDatabase::class.java,
           "DogsDB"
       ).build()
    }
}