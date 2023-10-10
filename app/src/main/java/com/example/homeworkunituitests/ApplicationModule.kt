package com.example.homeworkunituitests

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun getApiClient() = ApiClient()

    @Provides
    @Singleton
    fun getRepository(apiClient: ApiClient) = Repository(apiClient)
}
