package com.example.thedog.common

import android.app.Activity
import android.content.Intent
import com.example.thedog.R
import com.example.thedog.view.dogdetail.DogDetailActivity
import com.example.thedog.view.dogdetail.DogDetailActivity.Companion.EXTRA_DOG_ID

/**
 * Created by pedrooliveira on 09/12/2022
 * All rights reserved GoodBarber
 */
object NavigationUtils {

    fun navigateToDogDetail(activity: Activity, dogId: String) {
        activity.apply {
            startActivity(Intent(activity, DogDetailActivity::class.java).apply {
                putExtra(EXTRA_DOG_ID, dogId)
            })
            overridePendingTransition(R.anim.activity_forward_enter_lateral_animation, R.anim.activity_forward_exit_lateral_animation)
        }
    }
}