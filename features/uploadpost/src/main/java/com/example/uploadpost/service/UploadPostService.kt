package com.example.uploadpost.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.common.di.ComponentDepsProvider
import com.example.uploadpost.di.DaggerUploadPostComponent

class UploadPostService : Service() {

    override fun onCreate() {
        super.onCreate()

        DaggerUploadPostComponent.factory()
            .create(ComponentDepsProvider.get(context = this))
            .inject(this)
    }

    override fun onBind(p0: Intent?): IBinder? = null
}
