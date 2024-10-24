package com.example.uploadpost.di

import com.example.common.di.ComponentDeps
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher

interface UploadPostDeps : ComponentDeps {

    fun postRepository(): PostRepository

    fun userRepository(): UserRepository

    fun ioDispatcher(): CoroutineDispatcher
}
