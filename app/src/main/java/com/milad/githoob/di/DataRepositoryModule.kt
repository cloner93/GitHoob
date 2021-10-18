package com.milad.githoob.di

import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService): MainRepository {
        return MainRepository(apiService)
    }
}