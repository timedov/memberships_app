package com.example.savepost.di

import androidx.lifecycle.ViewModel
import com.example.savepost.presentation.SavePostViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface SavePostModule {

    @[Binds IntoMap ViewModelKey(SavePostViewModel::class)]
    fun bindViewModel(viewModel: SavePostViewModel): ViewModel
}
