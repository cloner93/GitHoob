package com.milad.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.milad.common.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreManager @Inject constructor( private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(AppConstants.PREFERENCES_STORE_NAME)

    private suspend fun <T> DataStore<Preferences>.getFromLocalStorage(PreferencesKey: Preferences.Key<T>): Flow<T?> {
        return data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map {
            it[PreferencesKey]
        }
    }

    suspend fun <T> storeValue(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit {
            it[key] = value
        }
    }

    suspend fun <T> readValue(key: Preferences.Key<T>): Flow<T?> {
        return context.dataStore.getFromLocalStorage(key)
    }
}