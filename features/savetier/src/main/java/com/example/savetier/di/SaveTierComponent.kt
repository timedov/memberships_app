package com.example.savetier.di

import com.example.common.di.FeatureScope
import com.example.savetier.presentation.SaveTierFragment
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    modules = [SaveTierModule::class],
    dependencies = [SaveTierDeps::class]
)
internal interface SaveTierComponent : SaveTierDeps {

    val viewModelProviderFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(deps: SaveTierDeps): SaveTierComponent
    }

    fun inject(saveTierFragment: SaveTierFragment): SaveTierFragment
}