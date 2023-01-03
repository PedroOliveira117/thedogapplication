package com.example.thedog

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.thedog.databinding.HomeActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity: AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navbarHost = supportFragmentManager.findFragmentById(
            R.id.view_container
        ) as NavHostFragment

        val states = arrayOf(intArrayOf(-android.R.attr.state_selected), intArrayOf(android.R.attr.state_selected))
        val statesColor = intArrayOf(Color.parseColor("#8c8c8c"), ContextCompat.getColor(applicationContext, R.color.app_text_color))

        // Bottom Navigation
        binding.viewBottomNavigation.apply {
            itemTextColor = ColorStateList(states, statesColor)
            itemIconTintList = ColorStateList(states, statesColor)
            itemRippleColor = null
            setupWithNavController(navbarHost.navController)
        }
    }
}