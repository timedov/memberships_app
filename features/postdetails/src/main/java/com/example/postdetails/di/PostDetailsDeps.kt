package com.example.postdetails.di

import com.example.common.di.ComponentDeps
import com.example.postdetails.navigation.PostDetailsRouter

interface PostDetailsDeps : ComponentDeps {

    fun postDetailsRouter(): PostDetailsRouter
}