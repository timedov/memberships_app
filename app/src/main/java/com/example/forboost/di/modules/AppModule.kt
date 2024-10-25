package com.example.forboost.di.modules

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.common.di.AppScope
import com.example.domain.di.DomainModule
import com.example.firebase.di.FirebaseModule
import com.example.forboost.ForBoostApp
import com.example.forboost.navigation.di.NavigationModule
import com.example.local.di.LocalModule
import com.example.network.di.NetworkModule
import dagger.Module
import dagger.Provides

@Module(includes = [
    DomainModule::class,
    FeatureDepsModule::class,
    FeaturesModule::class,
    NavigationModule::class,
    LocalModule::class,
    NetworkModule::class,
    FirebaseModule::class,
    LocalModule::class,
])
class AppModule {
    @AppScope
    @Provides
    fun provideContext(application: ForBoostApp): Context = application

    @Provides
    @AppScope
    fun provideVideoPlayer(context: Context): Player =
        ExoPlayer.Builder(context).build()
}