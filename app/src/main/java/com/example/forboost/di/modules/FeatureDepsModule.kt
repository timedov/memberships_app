package com.example.forboost.di.modules

import com.example.signin.impl.di.SignInDeps
import com.example.commentreplies.impl.di.CommentRepliesDeps
import com.example.common.di.ComponentDeps
import com.example.common.di.ComponentDepsKey
import com.example.feed.impl.di.FeedDeps
import com.example.forboost.di.components.AppComponent
import com.example.forboost.di.dependencies.DepsMap
import com.example.postdetails.impl.di.PostDetailsDeps
import com.example.profile.impl.di.ProfileDeps
import com.example.savepost.impl.di.SavePostDeps
import com.example.uploadpost.impl.di.UploadPostDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds

@Module
interface FeatureDepsModule {

    @Multibinds
    fun depsMap(): DepsMap

    @[Binds IntoMap ComponentDepsKey(SignInDeps::class)]
    fun bindAuthDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(FeedDeps::class)]
    fun bindFeedDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(ProfileDeps::class)]
    fun bindProfileDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(PostDetailsDeps::class)]
    fun bindsPostDetailsDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(CommentRepliesDeps::class)]
    fun bindsCommentRepliesDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SavePostDeps::class)]
    fun bindsSavePostDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(UploadPostDeps::class)]
    fun bindsUploadPostDeps(appComponent: AppComponent): ComponentDeps
}
