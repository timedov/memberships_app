package com.example.network.di

import com.example.network.remote.di.ApiDataModule
import dagger.Module

@Module(includes = [
    ApiDataModule::class
])
interface NetworkModule