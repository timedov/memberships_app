package com.example.forboost

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.common.di.ComponentDeps
import com.example.common.di.DepsContainer
import com.example.common.utils.Keys
import com.example.forboost.di.components.AppComponent
import com.example.forboost.di.components.DaggerAppComponent
import com.example.forboost.di.dependencies.ComponentDepsManager
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class ForBoostApp : Application(), DepsContainer {

    @Inject
    lateinit var depsManager: ComponentDepsManager

    @Inject
    lateinit var analytics: FirebaseAnalytics

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(application = this)
            .apply { inject(this@ForBoostApp) }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(
                    NotificationChannel(
                        Keys.UPLOAD_POST_CHANNEL_KEY,
                        getString(R.string.upload_post),
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
        }

        analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)
    }

    override fun <T : ComponentDeps> getDependencies(key: Class<T>): T {
        return depsManager.getDependencies(key)
    }
}