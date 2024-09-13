package com.example.forboost.di.modules

import com.example.forboost.features.feed.di.FeatureFeedBinderModule
import dagger.Module

@Module(includes = [FeatureFeedBinderModule::class])
class FeaturesModule