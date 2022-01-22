package com.milad.githoob.ui.launch

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.githoob.data.MainRepository
import com.milad.githoob.utils.AppConstants.KEY_DATA_STORE_TOKEN
import com.milad.githoob.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dataStore: DataStore<Preferences>,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _token = MutableLiveData<String>()
    var token: LiveData<String> = _token

    fun fetchToken(
        clientId: String,
        clientSecret: String,
        code: String
    ) {
        viewModelScope.launch(ioDispatcher) {
            mainRepository.getAccessToken(
                clientId,
                clientSecret,
                code
            ).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data.let {
                            if (it?.access_token != null && !it.access_token.equals("")) {
                                val token = "token ${it.access_token}"
                                _token.postValue(token)
                                saveToken(token)
                            }
                        }
                    }
                    Status.LOADING -> {
                    }
                    Status.ERROR -> {
                        Timber.d(it.message.toString())
                    }
                }
            }
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit {
            it[KEY_DATA_STORE_TOKEN] = token
        }
    }
}
