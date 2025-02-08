package com.example.forboost.di.dependencies

import com.example.signin.impl.di.SignInDeps
import com.example.commentreplies.impl.di.CommentRepliesDeps
import com.example.feed.impl.di.FeedDeps
import com.example.postdetails.impl.di.PostDetailsDeps
import com.example.profile.impl.di.ProfileDeps
import com.example.savepost.impl.di.SavePostDeps
import com.example.uploadpost.impl.di.UploadPostDeps

interface FeatureComponentsDeps :
    SignInDeps,
    CommentRepliesDeps,
    FeedDeps,
    PostDetailsDeps,
    ProfileDeps,
    SavePostDeps,
    UploadPostDeps
