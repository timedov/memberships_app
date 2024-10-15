package com.example.commentreplies.di

import androidx.lifecycle.ViewModel
import com.example.commentreplies.presentation.CommentRepliesViewModel
import com.example.ui.viewmodel.ViewModelKey
import com.example.ui.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
internal interface CommentRepliesModule {

    @[Binds IntoMap ViewModelKey(CommentRepliesViewModel::class)]
    fun bindViewModel(viewModel: CommentRepliesViewModel): ViewModel
}
