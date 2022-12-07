package com.example.thedog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.thedog.databinding.HomeActivityBinding
import com.example.thedog.view.doglist.DogBreedsListFragment
import com.example.thedog.view.dogsearch.DogSearchFragment
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        replaceFragment(DogBreedsListFragment())

        binding.viewBottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_list -> replaceFragment(DogBreedsListFragment())
                R.id.item_search -> replaceFragment(DogSearchFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.view_container, fragment)
        fragmentTransition.commit()
    }
}