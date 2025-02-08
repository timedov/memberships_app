package com.example.profile.impl.di

import com.example.feed.api.domain.repository.PostRepository
import com.example.profile.api.domain.repository.SubscribeRepository
import com.example.profile.api.domain.repository.UserRepository
import com.example.profile.api.navigation.ProfileRouter
import com.example.common.di.ComponentDeps
import com.example.data.api.user.datasource.AuthService
import kotlinx.coroutines.CoroutineDispatcher

interface ProfileDeps : ComponentDeps {

    fun profileRouter(): ProfileRouter

    fun authService(): AuthService

    fun postRepository(): PostRepository

    fun userRepository(): UserRepository

    fun subscribeRepository(): SubscribeRepository

    fun coroutineDispatcher(): CoroutineDispatcher
}