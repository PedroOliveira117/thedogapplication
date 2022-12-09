package com.example.thedog.model.cache

import com.example.thedog.model.data.Dog

/**
 * Created by pedrooliveira on 08/12/2022
 * All rights reserved GoodBarber
 */
object DogCacheManager {

    val DOG_LIST_FILE_PATH = "list.files"
    val DOG_SEARCH_FILE_PATH = "search.files"
    private val DOG_CACHE_SUFFIX = ".dog"

    /**
     * Receives a list of Dogs and saves each dog item in cache
     * Also saves a list with file paths in cache corresponding to each dog file
     **/
    fun saveInCache(dogList: ArrayList<Dog>, filePath: String) {
        if (dogList.isNotEmpty()) {
            val filePathCached = DiskCache.getObject(filePath)
            val filePathList = ArrayList<Any>()
            if (filePathCached != null && filePathCached is ArrayList<*>) {
                filePathList.addAll(filePathCached)
            }

            dogList.forEach { dog ->
                val fileName = dog.id.toString() + DOG_CACHE_SUFFIX
                DiskCache.saveObject(dog, fileName)
                if (!filePathList.contains(fileName)) {
                    filePathList.add(fileName)
                }
            }

            DiskCache.saveObject(filePathList, filePath)
        }
    }

    /**
     * Gets from cache a list of dogs corresponding to the filePath received
     * */
    fun getFromCache(filePath: String): ArrayList<Dog> {
        val dogCacheList = ArrayList<Dog>(arrayListOf())
        val filePathCached = DiskCache.getObject(filePath)
        if (filePathCached != null && filePathCached is ArrayList<*>) {
            filePathCached.forEach { path ->
                if (path is String) {
                    dogCacheList.add(DiskCache.getObject(path) as Dog)
                }
            }
            return dogCacheList
        }
        return dogCacheList
    }

    /**
     * Gets from cache a list of all dogs matching the breed received
     * Here first search on the list path cache and after on the search path cache and join all results
     * */
    fun getFromCacheByBreed(breed: String): ArrayList<Dog> {
        val dogListCache = getFromCache(DOG_LIST_FILE_PATH)
        val dogSearchCache = getFromCache(DOG_SEARCH_FILE_PATH)

        val dogListByBreed = ArrayList<Dog>(arrayListOf())

        dogListCache.forEach { dog ->
            if (dog.name?.lowercase()?.contains(breed.lowercase()) == true) {
                dogListByBreed.add(dog.copy())
            }
        }

        dogSearchCache.forEach { dog ->
            if (dog.name?.lowercase()?.contains(breed.lowercase()) == true && !dogListByBreed.contains(dog)) {
                dogListByBreed.add(dog.copy())
            }
        }
        return dogListByBreed
    }
}