package com.example.savepost.impl.di

import com.example.feed.api.domain.repository.PostRepository
import com.example.savepost.api.navigation.SavePostRouter
import com.example.common.di.ComponentDeps
import kotlinx.coroutines.CoroutineDispatcher

interface SavePostDeps : ComponentDeps {

    fun savePostRouter(): SavePostRouter

    fun postRepository(): PostRepository

    fun ioDispatcher(): CoroutineDispatcher
}
