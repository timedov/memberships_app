package com.example.postdetails.di

import androidx.media3.common.Player
import com.example.common.di.ComponentDeps
import com.example.domain.repository.CommentRepository
import com.example.domain.repository.FavoriteRepository
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.GetPostByIdUseCase
import com.example.domain.usecase.GetUserDetailsUseCase
import com.example.domain.usecase.IsUserSubscribedUseCase
import com.example.postdetails.navigation.PostDetailsRouter
import kotlinx.coroutines.CoroutineDispatcher

interface PostDetailsDeps : ComponentDeps {

    fun postDetailsRouter(): PostDetailsRouter

    fun player(): Player

    fun postRepository(): PostRepository

    fun userRepository(): UserRepository

    fun favoriteRepository(): FavoriteRepository

    fun commentRepository(): CommentRepository

    fun getPostByIdUseCase(): GetPostByIdUseCase

    fun getUserDetailsUseCase(): GetUserDetailsUseCase

    fun isUserSubscribedUseCase(): IsUserSubscribedUseCase

    fun ioDispatcher(): CoroutineDispatcher
}
