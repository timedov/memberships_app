package com.example.local.di

import com.example.local.comment.di.CommentLocalModule
import com.example.local.post.di.PostLocalModule
import dagger.Module

@Module(includes = [
    PostLocalModule::class,
    CommentLocalModule::class,
])
interface LocalModule