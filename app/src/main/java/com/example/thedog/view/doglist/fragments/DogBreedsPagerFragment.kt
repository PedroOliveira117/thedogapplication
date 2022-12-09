package com.example.thedog.view.doglist.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.thedog.R
import com.example.thedog.databinding.DogBreedsPagerFragmentBinding
import com.example.thedog.view.doglist.adapters.DogBreedsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsPagerFragment: Fragment(R.layout.dog_breeds_pager_fragment) {

    private lateinit var binding: DogBreedsPagerFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DogBreedsPagerFragmentBinding.bind(view)

        binding.apply {
            viewPager.adapter = DogBreedsPagerAdapter(requireActivity())

            TabLayoutMediator(viewTabLayout, viewPager) { tab, position ->
                when(position) {
                    0 -> {
                        tab.text = resources.getText(R.string.tab_layout_list)
                    }
                    1 -> {
                        tab.text = resources.getText(R.string.tab_layout_grid)
                    }
                }
            }.attach()
        }
    }
}