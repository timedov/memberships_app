package com.example.local.post

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.post.dao.PostDataDao
import com.example.local.post.entity.PostDataEntity

@Database(entities = [PostDataEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostDataDao
}
