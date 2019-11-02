package com.vvechirko.darkthemetest

import android.app.Application
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    val currentNightMode: Int
        get() = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
}