package com.example.savepost.di

import com.example.common.di.ComponentDeps
import com.example.savepost.navigation.SavePostRouter

interface SavePostDeps : ComponentDeps {

    fun savePostRouter(): SavePostRouter
}
