package com.example.savepost.di

import com.example.common.di.ComponentDeps
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import com.example.savepost.navigation.SavePostRouter
import kotlinx.coroutines.CoroutineDispatcher

interface SavePostDeps : ComponentDeps {

    fun savePostRouter(): SavePostRouter

    fun postRepository(): PostRepository

    fun userRepository(): UserRepository

    fun ioDispatcher(): CoroutineDispatcher
}
