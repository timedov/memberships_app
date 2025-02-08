package com.example.uploadpost.impl.di

import com.example.common.di.ComponentDeps
import com.example.common.utils.AppExceptionHandler
import com.example.feed.api.domain.repository.PostRepository
import com.example.profile.api.domain.repository.UserRepository
import com.example.uploadpost.api.domain.usecase.GetPostDraftUseCase
import com.example.uploadpost.api.domain.usecase.RemovePostDraftUseCase
import com.example.uploadpost.api.domain.usecase.UploadPostUseCase
import kotlinx.coroutines.CoroutineDispatcher

interface UploadPostDeps : ComponentDeps {

    fun postRepository(): PostRepository

    fun userRepository(): UserRepository

    fun getPostDraftUseCase(): GetPostDraftUseCase

    fun uploadPostUseCase(): UploadPostUseCase

    fun removePostDraftUseCase(): RemovePostDraftUseCase

    fun ioDispatcher(): CoroutineDispatcher

    fun appExceptionHandler(): AppExceptionHandler
}
