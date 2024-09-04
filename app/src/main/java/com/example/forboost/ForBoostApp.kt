package com.example.forboost

import android.app.Application
import com.example.forboost.di.components.AppComponent
import com.example.forboost.di.components.DaggerAppComponent

class ForBoostApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}