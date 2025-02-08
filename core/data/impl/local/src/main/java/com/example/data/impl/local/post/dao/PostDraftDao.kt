package com.example.data.impl.local.post.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.impl.local.post.entity.PostDraftEntity

@Dao
interface PostDraftDao {

    @Query("SELECT EXISTS (SELECT * FROM post_drafts)")
    suspend fun isPostDraftExists(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostDraft(postDraft: PostDraftEntity)

    @Query("SELECT * FROM post_drafts LIMIT 1")
    suspend fun getPostDraft(): PostDraftEntity?

    @Query("DELETE FROM post_drafts")
    suspend fun removePostDraft()
}
