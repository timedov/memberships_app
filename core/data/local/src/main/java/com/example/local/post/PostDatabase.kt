package com.example.local.post

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.post.dao.PostDataDao
import com.example.local.post.dao.PostDraftDao
import com.example.local.post.entity.PostDataEntity
import com.example.local.post.entity.PostDraftEntity

@Database(
    entities = [
        PostDataEntity::class,
        PostDraftEntity::class,
    ],
    version = 1
)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDataDao(): PostDataDao

    abstract fun postDraftDao(): PostDraftDao
}
