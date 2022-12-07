package com.example.thedog.doglist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.thedog.R
import com.example.thedog.databinding.DogListFragmentBinding

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogListFragment : Fragment(R.layout.dog_list_fragment) {

    private lateinit var binding: DogListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DogListFragmentBinding.bind(view)
    }
}