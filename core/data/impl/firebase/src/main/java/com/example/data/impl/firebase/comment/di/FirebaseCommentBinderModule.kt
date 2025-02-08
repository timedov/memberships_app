package com.example.data.impl.firebase.comment.di

import com.example.data.api.comment.datasource.CommentDataSource
import com.example.data.impl.firebase.comment.datasource.FirebaseCommentDataSource
import dagger.Binds
import dagger.Module

@Module
internal interface FirebaseCommentBinderModule {

    @Binds
    fun bindCommentLocalDataSourceImpl(commentLocalDataSourceImpl: FirebaseCommentDataSource): CommentDataSource
}