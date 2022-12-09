package com.example.thedog

import android.app.Application

/**
 * Created by pedrooliveira on 08/12/2022
 * All rights reserved GoodBarber
 */
class TheDogApplication: Application() {

    companion object {
        lateinit var initialize: Application
    }

    override fun onCreate() {
        super.onCreate()
        initialize = this
    }
}