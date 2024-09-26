package com.example.subscribe.presentation.model

sealed interface SubscribeAction {
    data object SubscribeSuccess : SubscribeAction
    data object SubscribeError : SubscribeAction
}