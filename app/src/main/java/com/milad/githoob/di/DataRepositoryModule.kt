package com.milad.githoob.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.milad.data.MainRepository
import com.milad.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService, datastore: DataStore<Preferences>): MainRepository {
        return MainRepository(apiService, datastore)
    }
}