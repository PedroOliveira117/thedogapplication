package com.example.thedog.dogsearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.thedog.R
import com.example.thedog.databinding.DogSearchFragmentBinding

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogSearchFragment : Fragment(R.layout.dog_search_fragment) {

    private lateinit var binding: DogSearchFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DogSearchFragmentBinding.bind(view)
    }
}