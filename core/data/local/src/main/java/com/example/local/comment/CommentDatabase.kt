package com.example.local.comment

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.comment.dao.CommentDao
import com.example.local.comment.entity.CommentEntity

@Database(entities = [CommentEntity::class], version = 1)
abstract class CommentDatabase : RoomDatabase() {

    abstract fun commentDao(): CommentDao
}