package com.example.savepost.impl.presentation.model

internal sealed interface SavePostAction {
    data object Initiate : SavePostAction
    data object ShowRestoreDraftDialog : SavePostAction
    data object SaveSuccess : SavePostAction
    data object SaveError : SavePostAction
}
