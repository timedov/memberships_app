package com.example.firebase.di

import com.example.common.di.AppScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides

@Module(includes = [FirebaseBinderModule::class])
class FirebaseModule {

    @Provides
    @AppScope
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @AppScope
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }
}