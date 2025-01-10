package com.verticalcoding.data.local.di

import android.content.Context
import androidx.room.Room
import com.verticalcoding.data.local.database.AppDatabase
import com.verticalcoding.data.local.database.DogDao
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
    fun provideDogDao(appDatabase: AppDatabase): DogDao {
        return appDatabase.dogDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
       return Room.databaseBuilder(
           appContext,
           AppDatabase::class.java,
           "DogsDB"
       ).build()
    }
}