package com.example.uploadpost.di

import com.example.uploadpost.service.UploadPostService
import dagger.Component

@Component(dependencies = [UploadPostDeps::class])
interface UploadPostComponent : UploadPostDeps {

    @Component.Factory
    interface Factory {

        fun create(deps: UploadPostDeps): UploadPostComponent
    }

    fun inject(service: UploadPostService): UploadPostService
}
