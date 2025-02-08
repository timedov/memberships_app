package com.example.signin.impl.di

import com.example.signin.api.domain.usecase.IsUserAuthorizedUseCase
import com.example.signin.api.domain.usecase.SignInUseCase
import com.example.data.api.user.datasource.AuthService
import com.example.signin.api.navigation.SignInRouter
import com.example.common.di.ComponentDeps
import kotlinx.coroutines.CoroutineDispatcher

interface SignInDeps : ComponentDeps {

    fun signInRouter(): SignInRouter

    fun authService(): AuthService

    fun isUserAuthorizedUseCase(): IsUserAuthorizedUseCase

    fun signInUseCase(): SignInUseCase

    fun coroutineDispatcher(): CoroutineDispatcher
}