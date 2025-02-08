package com.example.data.impl.firebase.post.di

import com.example.data.api.post.datasource.PostDataSource
import com.example.data.impl.firebase.post.datasource.FirebasePostDataSource
import dagger.Binds
import dagger.Module

@Module
internal interface FirebasePostBinderModule {

    @Binds
    fun bindFirebasePostDetailsDataSource(dataSource: FirebasePostDataSource): PostDataSource
}