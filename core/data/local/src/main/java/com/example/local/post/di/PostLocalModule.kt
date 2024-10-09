package com.example.local.post.di

import android.content.Context
import androidx.room.Room
import com.example.common.di.AppScope
import com.example.common.utils.Keys
import com.example.local.post.PostDatabase
import dagger.Module
import dagger.Provides

@Module(includes = [PostLocalBinderModule::class])
class PostLocalModule {

    @Provides
    @AppScope
    fun providePostDatabase(context: Context): PostDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PostDatabase::class.java,
            Keys.POST_DATABASE_KEY
        ).build()
    }
}
