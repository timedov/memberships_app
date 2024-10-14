package com.example.local.comment.di

import android.content.Context
import androidx.room.Room
import com.example.common.di.AppScope
import com.example.common.utils.Keys
import com.example.local.comment.CommentDatabase
import dagger.Module
import dagger.Provides

@Module(includes = [CommentLocalBinderModule::class])
class CommentLocalModule {

    @Provides
    @AppScope
    fun provideCommentDatabase(context: Context): CommentDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CommentDatabase::class.java,
            Keys.COMMENT_DATABASE_KEY
        ).build()
    }
}