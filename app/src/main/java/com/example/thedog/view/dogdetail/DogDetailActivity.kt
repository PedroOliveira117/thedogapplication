package com.example.thedog.view.dogdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.thedog.R
import com.example.thedog.databinding.DogDetailActivityBinding
import com.example.thedog.model.cache.DogDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DogDetailActivity: AppCompatActivity() {

    companion object {
        val EXTRA_DOG_ID = "dogId"
    }

    private lateinit var binding: DogDetailActivityBinding
    @Inject lateinit var dogDataBase: DogDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DogDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogId = intent.extras?.getString(EXTRA_DOG_ID)

        lifecycleScope.launch {
            val dog = dogDataBase.dogDao().getDogById(dogId.toString())
            binding.viewDogDetail.initUI(dog)
        }

        binding.viewBackButton.setOnClickListener {
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_back_enter_lateral_animation, R.anim.activity_back_exit_lateral_animation)
    }
}