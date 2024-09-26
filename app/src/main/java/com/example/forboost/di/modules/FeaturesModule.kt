package com.example.forboost.di.modules

import com.example.forboost.di.features.subscribe.di.FeatureSubscribeBinderModule
import dagger.Module

@Module(includes = [
    FeatureSubscribeBinderModule::class,
])
class FeaturesModule