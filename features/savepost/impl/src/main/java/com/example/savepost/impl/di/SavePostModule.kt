package com.example.savepost.impl.di

import androidx.lifecycle.ViewModel
import com.example.savepost.impl.presentation.SavePostViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
internal interface SavePostModule {

    @[Binds IntoMap ViewModelKey(SavePostViewModel::class)]
    fun bindViewModel(viewModel: SavePostViewModel): ViewModel
}
