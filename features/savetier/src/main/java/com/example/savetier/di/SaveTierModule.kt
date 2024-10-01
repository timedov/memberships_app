package com.example.savetier.di

import androidx.lifecycle.ViewModel
import com.example.savetier.presentation.SaveTierViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
internal interface SaveTierModule {

    @[Binds IntoMap ViewModelKey(SaveTierViewModel::class)]
    fun bindViewModel(viewModel: SaveTierViewModel): ViewModel
}