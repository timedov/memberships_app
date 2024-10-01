package com.example.forboost.di.modules

import com.example.forboost.features.auth.di.FeatureAuthBinderModule
import dagger.Module

@Module(includes = [
    FeatureAuthBinderModule::class,
])
class FeaturesModule