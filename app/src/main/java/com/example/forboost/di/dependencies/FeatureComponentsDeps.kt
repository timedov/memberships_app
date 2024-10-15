package com.example.forboost.di.dependencies

import com.example.auth.di.AuthDeps
import com.example.commentreplies.di.CommentRepliesDeps
import com.example.feed.di.FeedDeps
import com.example.profile.di.ProfileDeps
import com.example.subscribe.di.SubscribeDeps
import com.example.savetier.di.SaveTierDeps

interface FeatureComponentsDeps :
    AuthDeps,
    CommentRepliesDeps,
    FeedDeps,
    ProfileDeps,
    SubscribeDeps,
    SaveTierDeps
