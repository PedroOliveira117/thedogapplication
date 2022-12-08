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
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsViewModel(private val repository: DogBreedsRepository = DogBreedsRepository(dogBreedsApi = RetrofitInstance.dogBreedsApi)) : ViewModel() {

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
        if (hasMorePages && !isLoadingLiveData.value!!) {
            _isLoadingLiveData.value = true
            viewModelScope.launch {
                val response = repository.getDogBreeds(page = currentPage)
                _isLoadingLiveData.postValue(false)
                when (response) {
                    is Resource.Success -> {
                        if (response.data!!.size < 24) {
                            hasMorePages = false
                        } else {
                            currentPage++
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
