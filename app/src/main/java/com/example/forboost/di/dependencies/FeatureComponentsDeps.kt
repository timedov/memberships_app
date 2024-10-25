package com.example.forboost.di.dependencies

import com.example.auth.di.AuthDeps
import com.example.commentreplies.di.CommentRepliesDeps
import com.example.feed.di.FeedDeps
import com.example.postdetails.di.PostDetailsDeps
import com.example.profile.di.ProfileDeps
import com.example.savepost.di.SavePostDeps
import com.example.subscribe.di.SubscribeDeps
import com.example.savetier.di.SaveTierDeps
import com.example.uploadpost.di.UploadPostDeps

interface FeatureComponentsDeps :
    AuthDeps,
    CommentRepliesDeps,
    FeedDeps,
    PostDetailsDeps,
    ProfileDeps,
    SubscribeDeps,
    SavePostDeps,
    SaveTierDeps,
    UploadPostDeps
