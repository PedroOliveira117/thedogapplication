package com.example.thedog.model.repo

import com.example.thedog.common.Resource
import com.example.thedog.model.data.Dog
import com.example.thedog.model.data.DogDao
import com.example.thedog.model.interfaces.DogBreedsApi
import java.io.IOException

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsRepository(private val dogBreedsApi: DogBreedsApi, private val dogDao: DogDao) {

    suspend fun getDogBreeds(limit: Int, page: Int): Resource<ArrayList<Dog>> {
        val response = try {
            dogBreedsApi.getDogBreeds(limit, page)
        } catch (e: IOException) {
            return Resource.Error("An error occurred while getting dog breed", data = ArrayList(dogDao.getDogs(limit, page)))
        }
        saveDogsDatabase(response)
        return Resource.Success(response)
    }

    suspend fun searchDogBreed(query: String): Resource<ArrayList<Dog>> {
        val response = try {
            dogBreedsApi.searchDogBreed(query)
        } catch (e: IOException) {
            return Resource.Error("An error occurred while searching for breed: $query", data = ArrayList(dogDao.searchDogBreed(query)))
        }
        saveDogsDatabase(response)
        return Resource.Success(response)
    }

    private suspend fun saveDogsDatabase(dogList: ArrayList<Dog>) {
        dogList.forEach {
            dog -> dogDao.insertDog(dog)
        }
    }
}