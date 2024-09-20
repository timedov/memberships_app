package com.example.profile.di

import com.example.common.di.ComponentDeps
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import com.example.profile.navigation.ProfileRouter

interface ProfileDeps : ComponentDeps {

    fun profileRouter(): ProfileRouter

    fun userRepository(): UserRepository

    fun postRepository(): PostRepository
}