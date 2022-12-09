package com.example.thedog.view.dogdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thedog.R
import com.example.thedog.databinding.DogDetailActivityBinding

class DogDetailActivity: AppCompatActivity() {

    companion object {
        val EXTRA_DOG_ID = "dogId"
    }

    private lateinit var binding: DogDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DogDetailActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.viewBackButton.setOnClickListener {
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_back_enter_lateral_animation, R.anim.activity_back_exit_lateral_animation)
    }
}