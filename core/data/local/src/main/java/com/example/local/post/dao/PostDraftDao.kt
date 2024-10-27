package com.example.local.post.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.post.entity.PostDraftEntity

@Dao
interface PostDraftDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostDraft(postDraft: PostDraftEntity)

    @Query("SELECT * FROM post_drafts LIMIT 1")
    suspend fun getPostDraft(): PostDraftEntity?

    @Delete
    suspend fun removePostDraft(postDraft: PostDraftEntity)
}
