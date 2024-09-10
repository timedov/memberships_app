package com.example.auth.di

import androidx.lifecycle.ViewModel
import com.example.auth.presentation.login.LoginViewModel
import com.example.firebase.di.FirebaseModule
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(includes = [
    ViewModelModule::class,
    FirebaseModule::class
])
interface AuthModule {

    @[Binds IntoMap ViewModelKey(LoginViewModel::class)]
    fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}