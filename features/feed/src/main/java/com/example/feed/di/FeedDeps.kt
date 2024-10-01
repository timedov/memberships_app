package com.example.feed.di

import com.example.common.di.ComponentDeps
import com.example.domain.repository.PostRepository
import com.example.feed.navigation.FeedRouter

interface FeedDeps : ComponentDeps {

    fun feedRouter(): FeedRouter

    fun postRepository(): PostRepository
}