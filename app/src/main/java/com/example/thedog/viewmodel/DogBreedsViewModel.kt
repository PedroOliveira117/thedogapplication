package com.example.thedog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thedog.Utils.Resource
import com.example.thedog.model.data.Dog
import com.example.thedog.model.repo.DogBreedsRepository
import com.example.thedog.network.RetrofitInstance
import kotlinx.coroutines.launch

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsViewModel(private val repository: DogBreedsRepository = DogBreedsRepository(dogBreedsApi = RetrofitInstance.dogBreedsApi)): ViewModel() {

    private val _dogListLiveData = MutableLiveData<ArrayList<Dog>>(arrayListOf())
    val dogListLiveData: LiveData<ArrayList<Dog>> = _dogListLiveData
    private var _currentPage = 0

    init {
        getDogBreeds()
    }

    fun getDogBreeds() {
        // TODO Missing logic of pagination
        viewModelScope.launch {
            when (val response = repository.getDogBreeds(_currentPage)) {
                is Resource.Success -> {
                    _dogListLiveData.postValue(response.data)
                }
                is Resource.Error -> {

                }
            }
        }
    }
}