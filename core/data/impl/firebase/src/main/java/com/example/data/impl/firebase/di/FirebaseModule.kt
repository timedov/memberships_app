package com.example.data.impl.firebase.di

import com.example.common.di.AppScope
import com.example.data.impl.firebase.comment.di.FirebaseCommentModule
import com.example.data.impl.firebase.favorite.di.FirebaseFavoriteModule
import com.example.data.impl.firebase.post.di.FirebasePostModule
import com.example.data.impl.firebase.subscribe.di.FirebaseSubscribeModule
import com.example.data.impl.firebase.user.di.FirebaseUserModule
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        FirebaseBinderModule::class,
        FirebaseCommentModule::class,
        FirebaseFavoriteModule::class,
        FirebasePostModule::class,
        FirebaseSubscribeModule::class,
        FirebaseUserModule::class,
    ]
)
class FirebaseModule {

    @Provides
    @AppScope
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @AppScope
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @AppScope
    fun provideFirebaseCrashlytics(): FirebaseCrashlytics = Firebase.crashlytics

    @Provides
    @AppScope
    fun provideFirebaseAnalytics(): FirebaseAnalytics = Firebase.analytics
}