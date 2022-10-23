package com.milad.data.di

import com.milad.data.MainRepository
import com.milad.datastore.DataStoreManager
import com.milad.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataRepositoryModule {

    @Singleton
    @Provides
    fun provideDataRepository(apiService: ApiService, storeManager: DataStoreManager) =
        MainRepository(apiService, storeManager)

}