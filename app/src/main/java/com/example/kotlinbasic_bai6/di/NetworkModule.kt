package com.example.kotlinbasic_bai6.di

import com.example.kotlinbasic_bai6.network.ApiService
import com.example.kotlinbasic_bai6.network.RetrofitClient
import com.example.kotlinbasic_bai6.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule() {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.instance
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }

}