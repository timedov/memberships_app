package com.example.postdetails.di

import com.example.common.di.FeatureScope
import com.example.postdetails.presentation.PostDetailsFragment
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(modules = [PostDetailsModule::class], dependencies = [PostDetailsDeps::class])
internal interface PostDetailsComponent : PostDetailsDeps {

    val viewModelProviderFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(deps: PostDetailsDeps): PostDetailsComponent
    }

    fun inject(postDetailsFragment: PostDetailsFragment): PostDetailsFragment
}