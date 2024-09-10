package com.example.firebase.di

import com.example.domain.repository.UserRepository
import com.example.firebase.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface FirebaseBinderModule {

    @Binds
    fun bindUserRepositoryImpl_to_UserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}