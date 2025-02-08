package com.example.forboost.di.modules

import com.example.forboost.features.commentreplies.di.FeatureCommentRepliesBinderModule
import com.example.forboost.features.signin.di.FeatureSignInBinderModule
import com.example.forboost.features.feed.di.FeatureFeedBinderModule
import com.example.forboost.features.profile.di.FeatureProfileBinderModule
import com.example.forboost.features.postdetails.di.FeaturePostDetailsBinderModule
import com.example.forboost.features.savepost.di.FeatureSavePostBinderModule
import com.example.forboost.features.uploadpost.di.FeatureUploadPostBinderModule
import dagger.Module

@Module(includes = [
    FeatureSignInBinderModule::class,
    FeatureCommentRepliesBinderModule::class,
    FeatureFeedBinderModule::class,
    FeaturePostDetailsBinderModule::class,
    FeatureProfileBinderModule::class,
    FeatureSavePostBinderModule::class,
    FeatureUploadPostBinderModule::class,
])
class FeaturesModule
