package com.example.thedog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thedog.TheDogApplication
import com.example.thedog.model.cache.DogDatabase
import com.example.thedog.model.data.Dog
import com.example.thedog.model.repo.DogBreedsRepository
import com.example.thedog.network.RetrofitInstance
import kotlinx.coroutines.launch

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsViewModel(private val repository: DogBreedsRepository = DogBreedsRepository(dogBreedsApi = RetrofitInstance.dogBreedsApi, dogDao = DogDatabase.getDatabase(TheDogApplication.initialize.applicationContext).dogDao())) : ViewModel() {

    private val DOG_BREEDS_REQUEST_LIMIT = 24

    private val _dogListLiveData = MutableLiveData<ArrayList<Dog>>(arrayListOf())
    val dogListLiveData: LiveData<ArrayList<Dog>> = _dogListLiveData

    private val _isLoadingLiveData = MutableLiveData(false)
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    private var currentPage = 0
    private var hasMorePages = true

    init {
        getDogBreeds()
    }

    /**
     * Method used to get dog breeds per page
     * Default limit is 24 items per page
     * */
    fun getDogBreeds() {
        isLoadingLiveData.value?.let { isLoading ->
            if (hasMorePages && !isLoading) {
                _isLoadingLiveData.value = true
                viewModelScope.launch {
                    val response = repository.getDogBreeds(limit = DOG_BREEDS_REQUEST_LIMIT, page = currentPage)
                    _isLoadingLiveData.postValue(false)

                    response.data?.let { data ->
                        if (data.size < DOG_BREEDS_REQUEST_LIMIT) {
                            hasMorePages = false
                        } else {
                            currentPage++
                        }
                        _dogListLiveData.apply {
                            value?.addAll(data)
                            postValue(value)
                        }
                    }
                }
            }
        }
    }
}
