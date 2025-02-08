package com.example.forboost.features.signin.di

import com.example.signin.api.domain.usecase.IsUserAuthorizedUseCase
import com.example.signin.api.navigation.SignInRouter
import com.example.forboost.features.signin.AdapterSignInRouter
import com.example.signin.api.domain.usecase.SignInUseCase
import com.example.signin.impl.domain.usecase.IsUserAuthorizedUseCaseImpl
import com.example.signin.impl.domain.usecase.SignInUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface FeatureSignInBinderModule {

    @Binds
    fun bindAdapterSignInRouter(adapterSignInRouter: AdapterSignInRouter): SignInRouter

    @Binds
    fun bindIsUserAuthorizedUseCaseImpl(isUserAuthorizedUseCaseImpl: IsUserAuthorizedUseCaseImpl): IsUserAuthorizedUseCase

    @Binds
    fun bindSignInUseCaseImpl(signInUseCaseImpl: SignInUseCaseImpl): SignInUseCase
}
