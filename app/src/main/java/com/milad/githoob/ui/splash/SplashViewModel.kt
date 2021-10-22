package com.milad.githoob.ui.splash

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.lifecycle.ViewModel
import com.milad.githoob.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private var dataStore: DataStore<Preferences>?
) : ViewModel() {

    fun checkUserAuth() = dataStore!!.data.catch {
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[AppConstants.KEY_DATA_STORE_TOKEN] ?: ""
    }

    override fun onCleared() {
        super.onCleared()
        dataStore= null
    }
}
