package com.example.savetier.di

import com.example.common.di.ComponentDeps
import com.example.domain.repository.TierRepository
import com.example.domain.repository.UserRepository
import com.example.savetier.navigation.SaveTierRouter
import kotlinx.coroutines.CoroutineDispatcher

interface SaveTierDeps : ComponentDeps {

    fun saveTierRouter(): SaveTierRouter

    fun userRepository(): UserRepository

    fun tierRepository(): TierRepository

    fun coroutineDispatcher(): CoroutineDispatcher
}