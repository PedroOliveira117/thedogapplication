package com.example.thedog.di

import android.app.Application
import androidx.room.Room
import com.example.thedog.model.cache.DogDatabase
import com.example.thedog.model.interfaces.DogBreedsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by pedrooliveira on 03/01/2023
 * All rights reserved GoodBarber
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): DogBreedsApi = Retrofit.Builder()
        .baseUrl("https://api.thedogapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogBreedsApi::class.java)

    @Provides
    @Singleton
    fun provideDogDatabase(dogApplication: Application) = Room.databaseBuilder(dogApplication, DogDatabase::class.java, "dog_database").fallbackToDestructiveMigration().build()
}