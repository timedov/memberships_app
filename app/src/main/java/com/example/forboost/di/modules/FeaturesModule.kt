package com.example.forboost.di.modules

import com.example.forboost.di.features.savetier.di.FeatureSaveTierBinderModule
import dagger.Module

@Module(includes = [
    FeatureSaveTierBinderModule::class,
])
class FeaturesModule