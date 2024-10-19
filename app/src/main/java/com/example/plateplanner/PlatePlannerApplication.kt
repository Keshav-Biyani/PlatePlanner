package com.example.plateplanner

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlatePlannerApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        // Initialize any resources if needed
    }
}