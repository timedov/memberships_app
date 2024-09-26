package com.example.subscribe.di

import com.example.subscribe.presentation.SubscribeViewModel
import com.example.subscribe.presentation.SubscribeFragment
import dagger.Component

@Component(
    modules = [SubscribeModule::class],
    dependencies = [SubscribeDeps::class]
)
internal interface SubscribeComponent : SubscribeDeps {

    @Component.Factory
    interface Factory {

        fun create(deps: SubscribeDeps): SubscribeComponent
    }

    fun subscribeViewModel(): SubscribeViewModel.Factory

    fun inject(subscribeFragment: SubscribeFragment): SubscribeFragment
}
