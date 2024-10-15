package com.example.postdetails.di

import androidx.lifecycle.ViewModel
import com.example.postdetails.presentation.PostDetailsViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
internal interface PostDetailsModule {

    @[Binds IntoMap ViewModelKey(PostDetailsViewModel::class)]
    fun bindViewModel(viewModel: PostDetailsViewModel): ViewModel
}