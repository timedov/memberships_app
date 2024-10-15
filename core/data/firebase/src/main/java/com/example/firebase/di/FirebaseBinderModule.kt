package com.example.firebase.di

import com.example.domain.repository.CommentRepository
import com.example.domain.repository.FavoriteRepository
import com.example.domain.repository.SubscribeRepository
import com.example.domain.repository.UserRepository
import com.example.firebase.repository.CommentRepositoryImpl
import com.example.firebase.repository.FavoriteRepositoryImpl
import com.example.firebase.repository.SubscribeRepositoryImpl
import com.example.firebase.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface FirebaseBinderModule {

    @Binds
    fun bindUserRepositoryImplToUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindSubscribeRepositoryImplToSubscribeRepository(subscribeRepositoryImpl: SubscribeRepositoryImpl): SubscribeRepository

    @Binds
    fun bindFavoriteRepositoryImplToFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    fun bindCommentRepositoryImplToCommentRepository(commentRepositoryImpl: CommentRepositoryImpl): CommentRepository
}