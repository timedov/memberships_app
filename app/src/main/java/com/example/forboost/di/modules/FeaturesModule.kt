package com.example.forboost.di.modules

import com.example.forboost.di.features.commentreplies.di.FeatureCommentRepliesBinderModule
import com.example.forboost.features.auth.di.FeatureAuthBinderModule
import com.example.forboost.features.feed.di.FeatureFeedBinderModule
import com.example.forboost.di.features.profile.di.FeatureProfileBinderModule
import com.example.forboost.di.features.subscribe.di.FeatureSubscribeBinderModule
import com.example.forboost.di.features.savetier.di.FeatureSaveTierBinderModule
import dagger.Module

@Module(includes = [
    FeatureAuthBinderModule::class,
    FeatureCommentRepliesBinderModule::class,
    FeatureFeedBinderModule::class,
    FeatureProfileBinderModule::class,
    FeatureSubscribeBinderModule::class,
    FeatureSaveTierBinderModule::class,
])
class FeaturesModule