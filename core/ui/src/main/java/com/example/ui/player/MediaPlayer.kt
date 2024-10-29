package com.example.ui.player

import androidx.media3.common.Player
import com.example.ui.utils.urlToMediaItem
import javax.inject.Inject

class MediaPlayer @Inject constructor(
    private val player: Player
) {

    fun getPlayer() = player

    fun prepare() = player.prepare()

    fun play(content: String) {
        player.setMediaItem(content.urlToMediaItem())
    }

    fun release() = player.release()
}