package com.example.thedog.view.doglist.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thedog.view.doglist.fragments.DogBreedsGridFragment
import com.example.thedog.view.doglist.fragments.DogBreedsListFragment

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
class DogBreedsPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return DogBreedsListFragment()
        }
        return DogBreedsGridFragment()
    }
}