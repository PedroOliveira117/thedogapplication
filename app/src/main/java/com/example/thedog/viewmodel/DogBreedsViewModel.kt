package com.example.thedog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thedog.model.data.Dog
import com.example.thedog.model.repo.DogBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
@HiltViewModel
class DogBreedsViewModel @Inject constructor(private val repository: DogBreedsRepository) : ViewModel() {

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
