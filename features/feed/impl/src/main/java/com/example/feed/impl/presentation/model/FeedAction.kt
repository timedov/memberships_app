package com.example.feed.impl.presentation.model

sealed interface FeedAction {
    data object Initiate : FeedAction
}