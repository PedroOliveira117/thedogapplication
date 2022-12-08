package com.example.thedog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thedog.common.Resource
import com.example.thedog.model.data.Dog
import com.example.thedog.model.repo.DogBreedsRepository
import com.example.thedog.network.RetrofitInstance
import kotlinx.coroutines.launch

/**
 * Created by pedrooliveira on 08/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedSearchViewModel(private val repository: DogBreedsRepository = DogBreedsRepository(dogBreedsApi = RetrofitInstance.dogBreedsApi)): ViewModel() {

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
            when (response) {
                is Resource.Success -> {
                    _dogSearchListLiveData.postValue(response.data!!)
                }
                is Resource.Error -> {

                }
            }
        }
    }
}