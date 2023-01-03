package com.example.thedog.viewmodel

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
 * Created by pedrooliveira on 08/12/2022
 * All rights reserved GoodBarber
 */

@HiltViewModel
class DogBreedSearchViewModel @Inject constructor(private val repository: DogBreedsRepository): ViewModel() {

    private val _dogSearchListLiveData = MutableLiveData<ArrayList<Dog>>(arrayListOf())
    val dogSearchListLiveData: LiveData<ArrayList<Dog>> = _dogSearchListLiveData

    private val _isLoadingLiveData = MutableLiveData(false)
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    var query: String = ""

    /**
     * Method used to search for dog breed for a given query
     * */
    fun searchDogBreed(query: String) {
        this.query = query
        _isLoadingLiveData.value = true
        viewModelScope.launch {
            val response = repository.searchDogBreed(query)
            _isLoadingLiveData.postValue(false)
            response.data?.let { data ->
                _dogSearchListLiveData.postValue(data)
            }
        }
    }
}