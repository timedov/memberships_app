package com.example.local.di

import com.example.local.post.di.RoomPostModule
import dagger.Module

@Module(includes = [
    RoomPostModule::class,
])
interface RoomModule