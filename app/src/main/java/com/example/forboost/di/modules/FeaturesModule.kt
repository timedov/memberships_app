package com.example.forboost.di.modules

import com.example.forboost.di.features.profile.di.FeatureProfileBinderModule
import dagger.Module

@Module(includes = [
    FeatureProfileBinderModule::class,
])
class FeaturesModule