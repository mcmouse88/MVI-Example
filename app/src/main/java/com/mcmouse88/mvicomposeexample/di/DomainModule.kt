package com.mcmouse88.mvicomposeexample.di

import com.mcmouse88.mvicomposeexample.data.NoteRepositoryImpl
import com.mcmouse88.mvicomposeexample.data.firebase.AuthRepositoryImpl
import com.mcmouse88.mvicomposeexample.domain.repository.AuthRepository
import com.mcmouse88.mvicomposeexample.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface DomainModule {

    @[Binds Singleton]
    fun bindsDomainRepository(impl: NoteRepositoryImpl): NoteRepository

    @[Binds Singleton]
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}