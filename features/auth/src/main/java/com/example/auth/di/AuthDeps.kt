package com.example.auth.di

import com.example.auth.navigation.AuthRouter
import com.example.common.di.ComponentDeps

interface AuthDeps : ComponentDeps {

    fun authRouter(): AuthRouter
}