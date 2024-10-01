package com.example.auth.di

import com.example.auth.presentation.login.LoginFragment
import com.example.common.di.FeatureScope
import com.example.ui.viewmodel.ViewModelModule
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    dependencies = [AuthDeps::class],
    modules = [
        ViewModelModule::class,
        AuthModule::class,
    ]
)
internal interface AuthComponent {

    val viewModelFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(authDeps: AuthDeps): AuthComponent
    }

    fun inject(loginFragment: LoginFragment): LoginFragment
}