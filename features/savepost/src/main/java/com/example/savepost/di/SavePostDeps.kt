package com.example.savepost.di

import com.example.common.di.ComponentDeps
import com.example.domain.repository.PostRepository
import com.example.savepost.navigation.SavePostRouter
import com.example.ui.player.MediaPlayer
import kotlinx.coroutines.CoroutineDispatcher

interface SavePostDeps : ComponentDeps {

    fun savePostRouter(): SavePostRouter

    fun postRepository(): PostRepository

    fun mediaPlayer(): MediaPlayer

    fun ioDispatcher(): CoroutineDispatcher
}
