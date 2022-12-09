package com.example.thedog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.thedog.databinding.HomeActivityBinding

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navbarHost = supportFragmentManager.findFragmentById(
            R.id.view_container
        ) as NavHostFragment

        binding.viewBottomNavigation.setupWithNavController(navbarHost.navController)
    }
}