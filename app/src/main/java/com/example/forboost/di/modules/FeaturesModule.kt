package com.example.forboost.di.modules

import com.example.forboost.features.auth.di.FeatureAuthBinderModule
import com.example.forboost.features.feed.di.FeatureFeedBinderModule
import dagger.Module

@Module(includes = [
    FeatureAuthBinderModule::class,
    FeatureFeedBinderModule::class
])
class FeaturesModule