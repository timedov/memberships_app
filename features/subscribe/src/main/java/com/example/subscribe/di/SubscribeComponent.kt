package com.example.subscribe.di

import com.example.common.di.FeatureScope
import com.example.subscribe.presentation.SubscribeFragment
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    modules = [SubscribeModule::class],
    dependencies = [SubscribeDeps::class]
)
internal interface SubscribeComponent : SubscribeDeps {

    val viewModelProviderFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(deps: SubscribeDeps): SubscribeComponent
    }

    fun inject(subscribeFragment: SubscribeFragment): SubscribeFragment
}
