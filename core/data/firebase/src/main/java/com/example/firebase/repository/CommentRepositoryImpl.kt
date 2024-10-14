package com.example.firebase.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.common.utils.Constants
import com.example.common.utils.Keys
import com.example.domain.model.CommentDomainModel
import com.example.domain.repository.CommentRepository
import com.example.firebase.repository.mediator.CommentRemoteMediator
import com.example.firebase.utils.toFirestoreMap
import com.example.local.comment.CommentDatabase
import com.example.local.utils.toDomainModel
import com.example.local.utils.toEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val commentDatabase: CommentDatabase,
) : CommentRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCommentsByPostId(postId: Long): Flow<PagingData<CommentDomainModel>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE),
            remoteMediator = CommentRemoteMediator(
                postId = postId,
                commentDatabase = commentDatabase,
                firestore = firestore
            ),
            pagingSourceFactory = { commentDatabase.commentDao().getCommentsByPostId(postId) }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }
    }

    override suspend fun addComment(comment: CommentDomainModel) {
        commentDatabase.commentDao().insertComment(comment.toEntity())

        firestore.collection(Keys.COMMENTS_COLLECTION_KEY)
            .document(comment.id)
            .set(comment.toFirestoreMap())
            .await()
    }

    override suspend fun getCommentCountByPostId(postId: Long) =
        firestore.collection(Keys.COMMENTS_COLLECTION_KEY)
            .whereEqualTo(Keys.POST_ID_KEY, postId)
            .get()
            .await()
            .size()
}
