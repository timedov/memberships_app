package com.example.subscribe.di

import com.example.common.di.ComponentDeps
import com.example.domain.repository.SubscribeRepository
import com.example.domain.repository.TierRepository
import com.example.domain.repository.UserRepository
import com.example.subscribe.navigation.SubscribeRouter
import kotlinx.coroutines.CoroutineDispatcher

interface SubscribeDeps : ComponentDeps {

    fun subscribeRouter(): SubscribeRouter

    fun userRepository(): UserRepository

    fun tierRepository(): TierRepository

    fun subscribeRepository(): SubscribeRepository

    fun coroutineDispatcher(): CoroutineDispatcher
}