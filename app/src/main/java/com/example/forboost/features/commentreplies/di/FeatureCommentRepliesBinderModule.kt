package com.example.forboost.features.commentreplies.di

import com.example.commentreplies.navigation.CommentRepliesRouter
import com.example.forboost.features.commentreplies.AdapterCommentRepliesRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureCommentRepliesBinderModule {

    @Binds
    fun bindAdapterCommentRepliesRouterToCommentRepliesRouter(adapterCommentRepliesRouter: AdapterCommentRepliesRouter): CommentRepliesRouter
}
