package com.example.thedog.network

import com.example.thedog.model.interfaces.DogBreedsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
object RetrofitInstance {

    val dogBreedsApi: DogBreedsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogBreedsApi::class.java)
    }
}