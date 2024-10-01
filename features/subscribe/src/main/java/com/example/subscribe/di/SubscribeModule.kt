package com.example.subscribe.di

import androidx.lifecycle.ViewModel
import com.example.subscribe.presentation.SubscribeViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
internal interface SubscribeModule {

    @[Binds IntoMap ViewModelKey(SubscribeViewModel::class)]
    fun bindViewModel(viewModel: SubscribeViewModel): ViewModel
}