package com.example.forboost.di.features.commentreplies.di

import com.example.commentreplies.navigation.CommentRepliesRouter
import com.example.forboost.di.features.commentreplies.AdapterCommentRepliesRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureCommentRepliesBinderModule {

    @Binds
    fun bindAdapterCommentRepliesRouterToCommentRepliesRouter(adapterCommentRepliesRouter: AdapterCommentRepliesRouter): CommentRepliesRouter
}
