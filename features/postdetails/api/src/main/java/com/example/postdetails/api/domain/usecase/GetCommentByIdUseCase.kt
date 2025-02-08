package com.example.postdetails.api.domain.usecase

import com.example.postdetails.api.domain.model.CommentDomainModel

interface GetCommentByIdUseCase {

    suspend operator fun invoke(id: String): CommentDomainModel
}