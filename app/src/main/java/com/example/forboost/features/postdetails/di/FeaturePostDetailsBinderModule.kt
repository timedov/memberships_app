package com.example.forboost.features.postdetails.di

import com.example.forboost.features.postdetails.AdapterPostDetailsRouter
import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.postdetails.api.domain.repository.FavoriteRepository
import com.example.postdetails.api.domain.usecase.GetCommentByIdUseCase
import com.example.postdetails.api.domain.usecase.GetCommentRepliesUseCase
import com.example.postdetails.api.domain.usecase.SendCommentReplyUseCase
import com.example.postdetails.api.navigation.PostDetailsRouter
import com.example.postdetails.impl.data.repository.CommentRepositoryImpl
import com.example.postdetails.impl.data.repository.FavoriteRepositoryImpl
import com.example.postdetails.impl.domain.usecase.GetCommentByIdUseCaseImpl
import com.example.postdetails.impl.domain.usecase.GetCommentRepliesUseCaseImpl
import com.example.postdetails.impl.domain.usecase.SendCommentReplyUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface FeaturePostDetailsBinderModule {

    @Binds
    fun bindAdapterPostDetailsRouter(adapter: AdapterPostDetailsRouter): PostDetailsRouter

    @Binds
    fun bindCommentRepositoryImpl(commentRepositoryImpl: CommentRepositoryImpl): CommentRepository

    @Binds
    fun bindFavoriteRepositoryImpl(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    fun bindGetCommentByIdUseCaseImpl(getCommentByIdUseCaseImpl: GetCommentByIdUseCaseImpl): GetCommentByIdUseCase

    @Binds
    fun bindGetCommentRepliesUseCaseImpl(getCommentRepliesUseCaseImpl: GetCommentRepliesUseCaseImpl): GetCommentRepliesUseCase

    @Binds
    fun bindSendCommentReplyUseCaseImpl(sendCommentReplyUseCaseImpl: SendCommentReplyUseCaseImpl): SendCommentReplyUseCase

}