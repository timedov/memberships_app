package com.example.uploadpost.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.common.di.ComponentDepsProvider
import com.example.common.utils.Keys
import com.example.uploadpost.R
import com.example.uploadpost.di.DaggerUploadPostComponent
import com.example.uploadpost.usecase.UploadPostUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UploadPostService : Service() {

    @Inject
    lateinit var uploadPostUseCase: UploadPostUseCase

    @Inject
    lateinit var coroutineDispatcher: CoroutineDispatcher

    override fun onCreate() {
        super.onCreate()

        DaggerUploadPostComponent.factory()
            .create(ComponentDepsProvider.get(context = this))
            .inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(1, createLoadingNotification())

        CoroutineScope(coroutineDispatcher).launch {
            uploadPostUseCase.invoke()

            stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun createLoadingNotification(): Notification =
        NotificationCompat.Builder(this, Keys.UPLOAD_POST_CHANNEL_KEY)
            .setContentTitle(getString(R.string.uploading_post))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setProgress(0, 0, true)
            .build()

    override fun onBind(p0: Intent?): IBinder? = null
}
