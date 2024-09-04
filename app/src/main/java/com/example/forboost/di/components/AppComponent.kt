package com.example.forboost.di.components

import android.app.Application
import com.example.forboost.MainActivity
import com.example.forboost.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
annotation class AppScope

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
}