package com.example.ui.player

import androidx.media3.common.Player
import com.example.common.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class PlayerModule {

    @Provides
    @AppScope
    fun providePlayer(player: Player) = MediaPlayer(player)
}