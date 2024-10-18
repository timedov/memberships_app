package com.example.savepost.di

import com.example.common.di.FeatureScope
import com.example.savepost.presentation.SavePostFragment
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(modules = [SavePostModule::class], dependencies = [SavePostDeps::class])
interface SavePostComponent : SavePostDeps {

    val viewModelProviderFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(deps: SavePostDeps): SavePostComponent
    }

    fun inject(fragment: SavePostFragment): SavePostFragment
}
