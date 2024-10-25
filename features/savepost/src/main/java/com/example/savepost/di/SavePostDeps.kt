package com.example.savepost.di

import androidx.media3.common.Player
import com.example.common.di.ComponentDeps
import com.example.domain.repository.PostRepository
import com.example.savepost.navigation.SavePostRouter
import kotlinx.coroutines.CoroutineDispatcher

interface SavePostDeps : ComponentDeps {

    fun savePostRouter(): SavePostRouter

    fun postRepository(): PostRepository

    fun player(): Player

    fun ioDispatcher(): CoroutineDispatcher
}
