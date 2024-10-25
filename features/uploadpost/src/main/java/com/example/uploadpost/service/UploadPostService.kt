package com.example.uploadpost.service

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.common.di.ComponentDepsProvider
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.Keys
import com.example.common.utils.runSuspendCatching
import com.example.domain.model.PostDataDomainModel
import com.example.uploadpost.R
import com.example.uploadpost.di.DaggerUploadPostComponent
import com.example.uploadpost.usecase.GetPostDraftUseCase
import com.example.uploadpost.usecase.UploadPostUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class UploadPostService : Service() {

    @Inject
    lateinit var getPostDraftUseCase: GetPostDraftUseCase

    @Inject
    lateinit var uploadPostUseCase: UploadPostUseCase

    @Inject
    lateinit var coroutineDispatcher: CoroutineDispatcher

    @Inject
    lateinit var exceptionHandlerDelegate: ExceptionHandlerDelegate

    private val notificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun onCreate() {
        super.onCreate()

        DaggerUploadPostComponent.factory()
            .create(ComponentDepsProvider.get(context = this))
            .inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(
            1,
            createNotification(
                contentTitle = getString(R.string.uploading_post),
                showProgress = true,
                isOngoing = true
            )
        )

        loadPostDraft()

        return super.onStartCommand(intent, flags, startId)
    }

    private fun loadPostDraft() {
        CoroutineScope(coroutineDispatcher).launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                getPostDraftUseCase.invoke()
            }.onSuccess {
                uploadPost(it)
            }.onFailure {
                sendFailNotification()
                stopSelf()
            }
        }
    }

    private fun uploadPost(post: PostDataDomainModel) {
        CoroutineScope(coroutineDispatcher).launch {
            runSuspendCatching(exceptionHandlerDelegate) {
                uploadPostUseCase.invoke(
                    post = post,
                    content = File(post.content),
                    mimeType = contentResolver.getType(post.content.toUri()).orEmpty()
                )
            }.onSuccess {
                sendSuccessNotification()
            }.onFailure {
                sendFailNotification()
            }
            stopSelf()
        }
    }

    private fun createNotification(
        contentTitle: String,
        contentText: String = "",
        showProgress: Boolean = false,
        isOngoing: Boolean = false
    ): Notification =
        NotificationCompat
            .Builder(this, Keys.UPLOAD_POST_CHANNEL_KEY)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .apply { if (showProgress) setProgress(100, 0, true) }
            .setOngoing(isOngoing)
            .build()

    private fun sendSuccessNotification() {
        notificationManager.notify(
            2,
            createNotification(
                contentTitle = getString(R.string.upload_failed),
                contentText = getString(R.string.failed_post_upload_message)
            )
        )
    }

    private fun sendFailNotification() {
        notificationManager.notify(
            3,
            createNotification(
                contentTitle = getString(R.string.upload_failed),
                contentText = getString(R.string.failed_post_upload_message)
            )
        )
    }

    override fun onBind(p0: Intent?): IBinder? = null
}
