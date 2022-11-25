package com.milad.githoob.ui.launch

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.data.MainRepository
import com.milad.data.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val KEY_DATA_STORE_TOKEN = stringPreferencesKey("KEY_DATA_STORE_TOKEN")

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
            ).collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data.let {accessToken->
                            if (!accessToken?.access_token.isNullOrBlank()) {

                                val token = "token ${accessToken?.access_token}"
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

    private suspend fun saveToken(token: String) {
        mainRepository.saveDataStore(KEY_DATA_STORE_TOKEN, token)
    }
}
