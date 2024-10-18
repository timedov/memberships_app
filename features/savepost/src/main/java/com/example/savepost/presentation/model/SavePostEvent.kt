package com.example.savepost.presentation.model

sealed interface SavePostEvent {

    data object Initiate : SavePostEvent
}
