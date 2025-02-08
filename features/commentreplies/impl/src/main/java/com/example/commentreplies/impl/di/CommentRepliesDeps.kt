package com.example.commentreplies.impl.di

import com.example.postdetails.api.domain.usecase.GetCommentByIdUseCase
import com.example.postdetails.api.domain.usecase.GetCommentRepliesUseCase
import com.example.postdetails.api.domain.usecase.SendCommentReplyUseCase
import com.example.common.di.ComponentDeps
import com.example.commentreplies.api.navigation.CommentRepliesRouter

interface CommentRepliesDeps : ComponentDeps {

    fun commentRepliesRouter(): CommentRepliesRouter

    fun getCommentByIdUseCase(): GetCommentByIdUseCase

    fun getCommentRepliesUseCase(): GetCommentRepliesUseCase

    fun sendCommentReplyUseCase(): SendCommentReplyUseCase
}
