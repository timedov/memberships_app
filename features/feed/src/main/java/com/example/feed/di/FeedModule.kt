package com.example.feed.di

import androidx.lifecycle.ViewModel
import com.example.feed.presentation.FeedViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
])
internal interface FeedModule {

    @[Binds IntoMap ViewModelKey(FeedViewModel::class)]
    fun bindFeedViewModel(feedViewModel: FeedViewModel): ViewModel
}