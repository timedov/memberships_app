package com.example.data.impl.firebase.utils

import com.example.data.api.comment.model.CommentDataModel
import com.example.data.api.post.model.PostDataModel
import com.example.data.api.user.model.UserDataModel
import com.google.firebase.firestore.DocumentSnapshot

internal fun DocumentSnapshot.toCommentDataModel(): CommentDataModel =
    CommentDataModel(
        id = getString("id") ?: "",
        postId = getString("postId") ?: "",
        parentCommentId = getString("parentCommentId") ?: "",
        username = getString("username") ?: "",
        profileImageUrl = getString("profileImageUrl"),
        postedAt = getLong("postedAt") ?: 0L,
        body = getString("body") ?: "",
    )


internal fun DocumentSnapshot.toPostDataModel(): PostDataModel =
    PostDataModel(
        id = getString("id") ?: "",
        title = getString("title") ?: "",
        content = getString("content") ?: "",
        postedAt = getLong("postedAt") ?: -1L,
        author = getString("author") ?: "",
        body = getString("body") ?: "",
        requiresSubscription = getBoolean("requiresSubscription") ?: false
    )


internal fun DocumentSnapshot.toUserDataModel(): UserDataModel =
    UserDataModel(
        uid = getString("uid") ?: "",
        username = getString("username") ?: "",
        imageUrl = getString("imageUrl"),
        joinedAt = getLong("joinedAt") ?: -1L,
        about = getString("about") ?: ""
    )


