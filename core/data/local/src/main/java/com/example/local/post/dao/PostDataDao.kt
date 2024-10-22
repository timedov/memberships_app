package com.example.local.post.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.post.entity.PostDataEntity

@Dao
interface PostDataDao {
    @Query("SELECT * FROM posts WHERE id = :id")
    suspend fun getPostDataById(id: Long): PostDataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostData(postData: PostDataEntity)
}