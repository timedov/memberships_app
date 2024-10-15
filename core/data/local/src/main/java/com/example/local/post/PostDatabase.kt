package com.example.local.post

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.post.dao.PostDao
import com.example.local.post.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}
