package com.example.firebase.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.common.utils.Keys
import com.example.firebase.utils.toCommentEntity
import com.example.local.comment.CommentDatabase
import com.example.local.comment.entity.CommentEntity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalPagingApi::class)
class CommentRemoteMediator(
    private val postId: Long,
    private val commentDatabase: CommentDatabase,
    private val firestore: FirebaseFirestore,
) : RemoteMediator<Int, CommentEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CommentEntity>
    ): MediatorResult {
        return try {
            val query = firestore.collection(Keys.COMMENTS_COLLECTION_KEY)
                .whereEqualTo(Keys.POST_ID_KEY, postId)
                .orderBy(Keys.POSTED_AT_KEY, Query.Direction.DESCENDING)

            val snapshot = when (loadType) {
                LoadType.REFRESH -> {
                    query.limit(state.config.pageSize.toLong()).get().await()
                }
                LoadType.APPEND -> {
                    val lastVisibleComment = state.lastItemOrNull()
                    lastVisibleComment?.let {
                        query.startAfter(it.postedAt)
                            .limit(state.config.pageSize.toLong())
                            .get()
                            .await()
                    } ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val comments = snapshot.documents.mapNotNull { it.toCommentEntity() }

            commentDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    commentDatabase.commentDao().clearExpiredComments(postId)
                }
                commentDatabase.commentDao().insertAll(comments)
            }

            MediatorResult.Success(endOfPaginationReached = comments.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}

