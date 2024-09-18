package com.example.profile.di

import com.example.common.di.ComponentDeps
import com.example.profile.navigation.ProfileRouter

interface ProfileDeps : ComponentDeps {

    fun profileRouter() : ProfileRouter
}