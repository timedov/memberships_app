package com.example.profile.di

import androidx.lifecycle.ViewModel
import com.example.profile.presentation.ProfileViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
internal interface ProfileModule {

    @[Binds IntoMap ViewModelKey(ProfileViewModel::class)]
    fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel
}