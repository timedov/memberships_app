package com.example.forboost

import android.app.Application
import com.example.common.di.ComponentDeps
import com.example.common.di.DepsContainer
import com.example.forboost.di.components.AppComponent
import com.example.forboost.di.components.DaggerAppComponent
import com.example.forboost.di.dependencies.ComponentDepsManager
import javax.inject.Inject

class ForBoostApp : Application(), DepsContainer {

    @Inject
    lateinit var depsManager: ComponentDepsManager

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(application = this)
            .apply { inject(this@ForBoostApp) }
    }

    override fun <T : ComponentDeps> getDependencies(key: Class<T>): T {
        return depsManager.getDependencies(key)
    }
}