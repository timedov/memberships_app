package com.example.commentreplies.di

import com.example.commentreplies.navigation.CommentRepliesRouter
import com.example.common.di.ComponentDeps
import com.example.domain.repository.CommentRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher

interface CommentRepliesDeps : ComponentDeps {

    fun commentRepliesRouter(): CommentRepliesRouter

    fun commentRepository(): CommentRepository

    fun userRepository(): UserRepository

    fun ioDispatcher(): CoroutineDispatcher
}
