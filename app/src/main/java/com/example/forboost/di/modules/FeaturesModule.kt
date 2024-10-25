package com.example.forboost.di.modules

import com.example.forboost.features.commentreplies.di.FeatureCommentRepliesBinderModule
import com.example.forboost.features.auth.di.FeatureAuthBinderModule
import com.example.forboost.features.feed.di.FeatureFeedBinderModule
import com.example.forboost.features.profile.di.FeatureProfileBinderModule
import com.example.forboost.features.subscribe.di.FeatureSubscribeBinderModule
import com.example.forboost.features.savetier.di.FeatureSaveTierBinderModule
import com.example.forboost.features.postdetails.di.FeaturePostDetailsBinderModule
import com.example.forboost.features.savepost.di.FeatureSavePostBinderModule
import dagger.Module

@Module(includes = [
    FeatureAuthBinderModule::class,
    FeatureCommentRepliesBinderModule::class,
    FeatureFeedBinderModule::class,
    FeaturePostDetailsBinderModule::class,
    FeatureProfileBinderModule::class,
    FeatureSubscribeBinderModule::class,
    FeatureSaveTierBinderModule::class,
    FeatureSavePostBinderModule::class,
])
class FeaturesModule
