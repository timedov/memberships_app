package com.example.postdetails.api.domain.usecase

interface SendCommentReplyUseCase {

    suspend operator fun invoke(parentCommentId: String, comment: String)
}