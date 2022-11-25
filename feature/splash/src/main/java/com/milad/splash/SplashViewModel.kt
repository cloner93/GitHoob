package com.milad.splash

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import com.milad.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private var mainRepository: MainRepository
) : ViewModel() {
    private val KEY_DATA_STORE_TOKEN = stringPreferencesKey("KEY_DATA_STORE_TOKEN")

    suspend fun checkUserAuth() = mainRepository.readDataStore(KEY_DATA_STORE_TOKEN)
}
