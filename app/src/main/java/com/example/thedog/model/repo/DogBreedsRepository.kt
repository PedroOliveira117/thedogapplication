package com.example.thedog.model.repo

import com.example.thedog.Utils.Resource
import com.example.thedog.model.data.Dog
import com.example.thedog.model.interfaces.DogBreedsApi
import java.io.IOException

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsRepository(private val dogBreedsApi: DogBreedsApi) {

    suspend fun getDogBreeds(page: Int): Resource<ArrayList<Dog>> {
        val response = try {
            dogBreedsApi.getDogBreeds(page)
        } catch (e: IOException) {
            return Resource.Error("An error occurred while getting dog breed")
        }
        return Resource.Success(response)
    }
}