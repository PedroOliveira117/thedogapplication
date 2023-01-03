package com.example.thedog.model.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.thedog.model.data.Dog
import com.example.thedog.model.data.DogDao

/**
 * Created by pedrooliveira on 02/01/2023
 * All rights reserved GoodBarber
 */

@Database(
    entities = [Dog::class],
    version = 1
)

abstract class DogDatabase: RoomDatabase() {

    abstract fun dogDao(): DogDao
}