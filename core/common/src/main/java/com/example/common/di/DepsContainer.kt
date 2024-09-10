package com.example.common.di

interface DepsContainer {
    fun <T : ComponentDeps> getDependencies(key: Class<T>): T
}