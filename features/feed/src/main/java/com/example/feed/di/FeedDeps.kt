package com.example.feed.di

import com.example.common.di.ComponentDeps
import com.example.domain.repository.CommentRepository
import com.example.domain.repository.PostRepository
import com.example.feed.navigation.FeedRouter
import kotlinx.coroutines.CoroutineDispatcher

interface FeedDeps : ComponentDeps {

    fun feedRouter(): FeedRouter

    fun postRepository(): PostRepository

    fun commentRepository(): CommentRepository

    fun ioDispatcher(): CoroutineDispatcher
}
