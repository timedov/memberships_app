package com.example.commentreplies.impl.di

import androidx.lifecycle.ViewModel
import com.example.commentreplies.impl.presentation.CommentRepliesViewModel
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
