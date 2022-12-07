package com.example.thedog.viewmodel

import android.util.Log
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
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsViewModel(private val repository: DogBreedsRepository = DogBreedsRepository(dogBreedsApi = RetrofitInstance.dogBreedsApi)) : ViewModel() {

    private val _dogListLiveData = MutableLiveData<ArrayList<Dog>>(arrayListOf())
    val dogListLiveData: LiveData<ArrayList<Dog>> = _dogListLiveData

    private var _currentPage = 0
    private var _hasMorePages = true
    private var _isLoading = false

    init {
        getDogBreeds()
    }

    /**
     * Method used to get dog breeds per page
     * Default limit is 24 items per page
     * */
    fun getDogBreeds() {
        if (_hasMorePages && !_isLoading) {
            _isLoading = true
            viewModelScope.launch {
                val response = repository.getDogBreeds(page = _currentPage)
                _isLoading = false
                when (response) {
                    is Resource.Success -> {
                        if (response.data!!.size < 24) {
                            _hasMorePages = false
                        } else {
                            _currentPage++
                        }
                        _dogListLiveData.apply {
                            value!!.addAll(response.data)
                            postValue(value)
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }
}
