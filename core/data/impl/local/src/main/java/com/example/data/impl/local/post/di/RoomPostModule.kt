package com.example.local.post.di

import android.content.Context
import androidx.room.Room
import com.example.common.di.AppScope
import com.example.common.utils.Keys
import com.example.data.impl.local.post.PostDatabase
import dagger.Module
import dagger.Provides

@Module(includes = [RoomPostBinderModule::class])
class RoomPostModule {

    @Provides
    @AppScope
    fun providePostDatabase(context: Context): PostDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PostDatabase::class.java,
            Keys.POST_DATABASE_KEY
        ).build()
    }

    @Provides
    @AppScope
    fun providePostDraftDao(postDatabase: PostDatabase) = postDatabase.postDraftDao()
}
