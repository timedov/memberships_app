package com.example.commentreplies.di

import com.example.commentreplies.presentation.CommentRepliesFragment
import com.example.common.di.FeatureScope
import com.example.ui.viewmodel.ViewModelProviderFactory
import dagger.Component

@FeatureScope
@Component(
    modules = [CommentRepliesModule::class],
    dependencies = [CommentRepliesDeps::class]
)
internal interface CommentRepliesComponent : CommentRepliesDeps {

    val viewModelProviderFactory: ViewModelProviderFactory

    @Component.Factory
    interface Factory {

        fun create(deps: CommentRepliesDeps): CommentRepliesComponent
    }

    fun inject(fragment: CommentRepliesFragment): CommentRepliesFragment
}
