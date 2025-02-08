package com.example.uploadpost.impl.service

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.common.di.ComponentDepsProvider
import com.example.common.utils.AppExceptionHandler
import com.example.common.utils.Keys
import com.example.common.utils.runSuspendCatching
import com.example.feed.api.domain.model.PostDomainModel
import com.example.uploadpost.api.domain.usecase.GetPostDraftUseCase
import com.example.uploadpost.api.domain.usecase.RemovePostDraftUseCase
import com.example.uploadpost.api.domain.usecase.UploadPostUseCase
import com.example.uploadpost.impl.R
import com.example.uploadpost.impl.di.DaggerUploadPostComponent
import com.example.uploadpost.impl.di.UploadPostComponent
import com.example.uploadpost.impl.utils.toBase64
import kotlinx.coroutines.*

class UploadPostService : Service() {

    private lateinit var component: UploadPostComponent
    private lateinit var getPostDraftUseCase: GetPostDraftUseCase
    private lateinit var uploadPostUseCase: UploadPostUseCase
    private lateinit var removePostDraftUseCase: RemovePostDraftUseCase
    private lateinit var coroutineDispatcher: CoroutineDispatcher
    private lateinit var appExceptionHandler: AppExceptionHandler

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var notificationManager: NotificationManager? = null

    override fun onCreate() {
        super.onCreate()

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        component = DaggerUploadPostComponent.factory()
            .create(ComponentDepsProvider.get(applicationContext))

        getPostDraftUseCase = component.getPostDraftUseCase()
        uploadPostUseCase = component.uploadPostUseCase()
        removePostDraftUseCase = component.removePostDraftUseCase()
        coroutineDispatcher = component.ioDispatcher()
        appExceptionHandler = component.appExceptionHandler()
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

        return START_NOT_STICKY
    }

    private fun loadPostDraft() {
        serviceScope.launch {
            runSuspendCatching(appExceptionHandler) {
                getPostDraftUseCase.invoke()
            }.onSuccess {
                uploadPost(it)
            }.onFailure {
                sendFailNotification()
                stopSelf()
            }
        }
    }

    private fun uploadPost(post: PostDomainModel) {
        serviceScope.launch {
            runSuspendCatching(appExceptionHandler) {
                uploadPostUseCase.invoke(
                    post = PostDomainModel(
                        id = post.id,
                        title = post.title,
                        content = post.content.toBase64(this@UploadPostService).orEmpty(),
                        body = post.body,
                        requiresSubscription = post.requiresSubscription,
                        author = post.author,
                    )
                )
            }.onSuccess {
                sendSuccessNotification()
                removePostDraft()
            }.onFailure {
                sendFailNotification()
                stopSelf()
            }
        }
    }

    private fun removePostDraft() {
        serviceScope.launch {
            runSuspendCatching(appExceptionHandler) {
                removePostDraftUseCase.invoke()
            }.onSuccess {
                stopSelf()
            }
        }
    }

    private fun createNotification(
        contentTitle: String,
        contentText: String = "",
        showProgress: Boolean = false,
        isOngoing: Boolean = false
    ): Notification =
        NotificationCompat.Builder(this, Keys.UPLOAD_POST_CHANNEL_KEY)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .apply { if (showProgress) setProgress(100, 0, true) }
            .setOngoing(isOngoing)
            .build()

    private fun sendSuccessNotification() {
        notificationManager?.notify(
            2,
            createNotification(
                contentTitle = getString(R.string.post_uploaded),
                contentText = getString(R.string.post_uploaded_successfully),
            )
        )
    }

    private fun sendFailNotification() {
        notificationManager?.notify(
            3,
            createNotification(
                contentTitle = getString(R.string.post_upload_failed),
                contentText = getString(R.string.failed_to_upload_post),
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    override fun onBind(p0: Intent?): IBinder? = null
}
