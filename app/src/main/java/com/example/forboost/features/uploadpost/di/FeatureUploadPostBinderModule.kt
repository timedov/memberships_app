package com.example.forboost.features.uploadpost.di

import com.example.uploadpost.api.domain.usecase.GetPostDraftUseCase
import com.example.uploadpost.api.domain.usecase.RemovePostDraftUseCase
import com.example.uploadpost.api.domain.usecase.UploadPostUseCase
import com.example.uploadpost.impl.domain.usecase.GetPostDraftUseCaseImpl
import com.example.uploadpost.impl.domain.usecase.RemovePostDraftUseCaseImpl
import com.example.uploadpost.impl.domain.usecase.UploadPostUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface FeatureUploadPostBinderModule {

    @Binds
    fun bindGetPostDraftUseCaseImpl(getPostDraftUseCaseImpl: GetPostDraftUseCaseImpl): GetPostDraftUseCase

    @Binds
    fun bindUploadPostUseCaseImpl(uploadPostUseCaseImpl: UploadPostUseCaseImpl): UploadPostUseCase

    @Binds
    fun bindRemovePostDraftUseCase(removePostDraftUseCase: RemovePostDraftUseCaseImpl): RemovePostDraftUseCase
}