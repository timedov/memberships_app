package com.example.forboost.navigation.di

import com.example.common.di.AppScope
import com.example.forboost.navigation.GlobalRouter
import com.example.forboost.navigation.Navigator
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @AppScope
    @Provides
    fun provideNavigator(): Navigator = Navigator()

    @AppScope
    @Provides
    fun provideGlobalRouter(navigator: Navigator): GlobalRouter = navigator
}