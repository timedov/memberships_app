package com.example.network.di

import com.example.network.remote.di.PostDataModule
import dagger.Module

@Module(includes = [
    PostDataModule::class
])
class NetworkModule