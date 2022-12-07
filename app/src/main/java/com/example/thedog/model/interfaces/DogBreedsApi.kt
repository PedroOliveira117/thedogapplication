package com.example.thedog.model.interfaces

import com.example.thedog.model.data.Dog
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
interface DogBreedsApi {

    @GET("/v1/breeds?limit=24")
    suspend fun getDogBreeds(@Query("page") page: Int): ArrayList<Dog> = arrayListOf()
}