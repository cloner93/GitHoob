package com.milad.githoob.ui.launch

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.milad.githoob.data.MainRepository
import com.milad.githoob.utils.AppConstants.KEY_DATA_STORE_TOKEN
import com.milad.githoob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dataStore: DataStore<Preferences>,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    fun fetchToken(
        clientId: String,
        clientSecret: String,
        code: String
    ) = liveData(ioDispatcher) {
        emit(Resource.loading(null))
        try {
            emit(
                Resource.success(
                    data = mainRepository.getAccessToken(
                        clientId,
                        clientSecret,
                        code
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                dataStore.edit {
                    it[KEY_DATA_STORE_TOKEN] = token
                }
            }
        }
    }
}
