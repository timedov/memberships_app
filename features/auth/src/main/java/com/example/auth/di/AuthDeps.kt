package com.example.auth.di

import com.example.auth.navigation.AuthRouter
import com.example.common.di.ComponentDeps
import com.example.domain.repository.UserRepository

interface AuthDeps : ComponentDeps {

    fun authRouter(): AuthRouter

    fun userRepository(): UserRepository
}