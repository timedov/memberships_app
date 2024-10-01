package com.example.profile.di

import com.example.profile.presentation.ProfileFragment
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@Component(
    modules = [ProfileModule::class],
    dependencies = [ProfileDeps::class]
)
internal interface ProfileComponent : ProfileDeps {

    val viewModelFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(profileDeps: ProfileDeps): ProfileComponent
    }

    fun inject(feedFragment: ProfileFragment): ProfileFragment
}
