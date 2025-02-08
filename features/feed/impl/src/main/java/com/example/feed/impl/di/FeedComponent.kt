package com.example.feed.impl.di

import com.example.common.di.FeatureScope
import com.example.feed.impl.presentation.FeedFragment
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    modules = [FeedModule::class],
    dependencies = [FeedDeps::class]
    )
internal interface FeedComponent : FeedDeps {

    val viewModelFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(feedDeps: FeedDeps): FeedComponent
    }

    fun inject(feedFragment: FeedFragment): FeedFragment
}