package com.example.forboost.di.components

import android.app.Application
import com.example.common.di.AppScope
import com.example.forboost.ForBoostApp
import com.example.forboost.MainActivity
import com.example.forboost.di.dependencies.FeatureComponentsDeps
import com.example.forboost.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : FeatureComponentsDeps {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: ForBoostApp): AppComponent
    }

    fun inject(application: Application)

    fun inject(mainActivity: MainActivity)
}