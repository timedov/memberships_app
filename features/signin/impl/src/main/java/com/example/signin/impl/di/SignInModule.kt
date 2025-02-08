package com.example.signin.impl.di

import androidx.lifecycle.ViewModel
import com.example.signin.impl.presentation.SignInViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(includes = [
    ViewModelModule::class,
])
internal interface SignInModule {

    @[Binds IntoMap ViewModelKey(SignInViewModel::class)]
    fun bindLoginViewModel(signInViewModel: SignInViewModel): ViewModel
}