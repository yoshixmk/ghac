package com.example.ghac.di

import com.example.ghac.data.repository.GithubRepositoryRepositoryImpl
import com.example.ghac.data.repository.GithubUserRepositoryImpl
import com.example.ghac.domain.repository.GithubRepositoryRepository
import com.example.ghac.domain.repository.GithubUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindGithubUserRepositoryProvider(provider: GithubUserRepositoryImpl): GithubUserRepository

    @Binds
    @Singleton
    fun bindGithubRepositoryRepositoryProvider(provider: GithubRepositoryRepositoryImpl): GithubRepositoryRepository
}
