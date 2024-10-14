package com.example.local.di

import com.example.local.comment.di.CommentLocalModule
import dagger.Module

@Module(includes = [
    CommentLocalModule::class,
])
interface LocalModule