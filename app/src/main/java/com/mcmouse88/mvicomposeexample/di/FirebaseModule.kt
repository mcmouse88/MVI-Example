package com.mcmouse88.mvicomposeexample.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class FirebaseModule {

    @[Provides Singleton]
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @[Provides Singleton]
    fun proviseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()


}