package com.example.data.impl.local.post

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.impl.local.post.dao.PostDraftDao
import com.example.data.impl.local.post.entity.PostDraftEntity

@Database(
    entities = [
        PostDraftEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDraftDao(): PostDraftDao
}
