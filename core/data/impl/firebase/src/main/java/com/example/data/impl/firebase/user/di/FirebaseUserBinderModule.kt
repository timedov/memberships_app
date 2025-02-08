package com.example.data.impl.firebase.user.di

import com.example.data.api.user.datasource.AuthService
import com.example.data.api.user.datasource.UserDataSource
import com.example.data.impl.firebase.user.datasource.FirebaseAuthService
import com.example.data.impl.firebase.user.datasource.FirebaseUserDataSource
import dagger.Binds
import dagger.Module

@Module
internal interface FirebaseUserBinderModule {

   @Binds
   fun bindUserDataSourceImpl(dataSource: FirebaseUserDataSource): UserDataSource

   @Binds
   fun bindAuthServiceImpl(service: FirebaseAuthService): AuthService
}