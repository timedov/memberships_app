package com.example.subscribe.di

import com.example.ui.viewmodel.ViewModelModule
import dagger.Module

@Module(includes = [ViewModelModule::class])
internal interface SubscribeModule