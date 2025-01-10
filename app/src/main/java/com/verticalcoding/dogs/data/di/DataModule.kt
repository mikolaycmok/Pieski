package com.verticalcoding.dogs.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton // Dagger will make sure in this scenario that this is single instance
    @Binds // This will bind all places Injecting dogRepository interface to the Default implementation
    fun bindsDogRepository(
        dogRepository: com.verticalcoding.dogs.data.DefaultDogRepository
    ) : com.verticalcoding.dogs.data.DogRepository
}