package com.example.forboost

import android.app.Application
import com.example.forboost.di.components.DaggerAppComponent

class ForBoostApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.factory()
            .create(application = this)
            .inject(this)
    }
}