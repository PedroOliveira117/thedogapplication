package com.example.thedog.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by pedrooliveira on 02/01/2023
 * All rights reserved GoodBarber
 */

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: Dog)

    @Delete
    suspend fun deleteDog(dog: Dog)

    @Query("SELECT * FROM dog_database ORDER BY name ASC LIMIT :limit OFFSET :page * :limit")
    suspend fun getDogs(limit: Int, page: Int): List<Dog>

    @Query("SELECT * FROM dog_database WHERE id LIKE :id")
    suspend fun getDogById(id: String): Dog

    @Query("SELECT * FROM dog_database WHERE name LIKE '%' || :breed || '%' ORDER BY name ASC")
    suspend fun searchDogBreed(breed: String): List<Dog>
}