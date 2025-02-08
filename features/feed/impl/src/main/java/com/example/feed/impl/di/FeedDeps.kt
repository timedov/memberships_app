package com.example.feed.impl.di

import com.example.common.di.ComponentDeps
import com.example.feed.api.domain.repository.PostRepository
import com.example.feed.api.domain.usecase.GetPostsUseCase
import com.example.feed.api.navigation.FeedRouter

interface FeedDeps : ComponentDeps {

    fun feedRouter(): FeedRouter

    fun postRepository(): PostRepository

    fun getPostsUseCase(): GetPostsUseCase
}