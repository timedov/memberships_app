package com.example.signin.impl.di

import com.example.signin.impl.presentation.SignInFragment
import com.example.common.di.FeatureScope
import com.example.ui.viewmodel.ViewModelModule
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    dependencies = [SignInDeps::class],
    modules = [
        ViewModelModule::class,
        SignInModule::class,
    ]
)
internal interface SignInComponent: SignInDeps {

    val viewModelFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(signInDeps: SignInDeps): SignInComponent
    }

    fun inject(signInFragment: SignInFragment): SignInFragment
}