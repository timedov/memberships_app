package com.example.forboost.di.dependencies

import com.example.auth.di.AuthDeps
import com.example.feed.di.FeedDeps
import com.example.profile.di.ProfileDeps
import com.example.subscribe.di.SubscribeDeps

interface FeatureComponentsDeps : AuthDeps, FeedDeps, ProfileDeps, SubscribeDeps