package com.example.postdetails.impl.di

import com.example.postdetails.api.domain.repository.CommentRepository
import com.example.postdetails.api.domain.repository.FavoriteRepository
import com.example.feed.api.domain.repository.PostRepository
import com.example.profile.api.domain.repository.SubscribeRepository
import com.example.profile.api.domain.repository.UserRepository
import com.example.postdetails.api.navigation.PostDetailsRouter
import com.example.common.di.ComponentDeps
import kotlinx.coroutines.CoroutineDispatcher

interface PostDetailsDeps : ComponentDeps {

    fun postDetailsRouter(): PostDetailsRouter

    fun postRepository(): PostRepository

    fun userRepository(): UserRepository

    fun subscribeRepository(): SubscribeRepository

    fun favoriteRepository(): FavoriteRepository

    fun commentRepository(): CommentRepository

    fun coroutineDispatcher(): CoroutineDispatcher
}
