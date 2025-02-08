package com.example.uploadpost.impl.di

import com.example.uploadpost.impl.service.UploadPostService
import dagger.Component

@Component(dependencies = [UploadPostDeps::class])
internal interface UploadPostComponent : UploadPostDeps {

    @Component.Factory
    interface Factory {

        fun create(deps: UploadPostDeps): UploadPostComponent
    }

    fun inject(service: UploadPostService): UploadPostService
}
