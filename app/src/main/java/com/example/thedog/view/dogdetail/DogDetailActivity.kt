package com.example.thedog.view.dogdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thedog.R
import com.example.thedog.databinding.DogDetailActivityBinding
import com.example.thedog.model.cache.DogCacheManager

class DogDetailActivity: AppCompatActivity() {

    companion object {
        val EXTRA_DOG_ID = "dogId"
    }

    private lateinit var binding: DogDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DogDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogId = intent.extras?.getString(EXTRA_DOG_ID)
        val dog = DogCacheManager.getFromCacheById(dogId.toString())

        binding.viewBackButton.setOnClickListener {
            finish()
        }
        if (dog != null) {
            binding.viewDogDetail.initUI(dog)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_back_enter_lateral_animation, R.anim.activity_back_exit_lateral_animation)
    }
}