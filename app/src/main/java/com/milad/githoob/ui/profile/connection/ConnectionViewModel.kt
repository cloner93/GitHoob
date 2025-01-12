package com.milad.githoob.ui.profile.connection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.data.MainRepository
import com.milad.data.utils.Status
import com.milad.data.utils.Result
import com.milad.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ConnectionViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private var _connectionList = MutableLiveData<ArrayList<User>>()

    val connectionList: LiveData<ArrayList<User>> = _connectionList
    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun setUser(token: String = "", userId: String = "", type: String) {
        getStarredRepo(token, userId, type)
    }

    private fun getStarredRepo(
        token: String,
        userId: String,
        type: String
    ) {
        viewModelScope.launch(ioDispatcher) {
            getConnections(token, userId, type).collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        _connectionList.postValue((it.data!!))
                        _dataLoading.postValue(false)
                    }
                    Status.LOADING -> {
                        _dataLoading.postValue(true)
                    }
                    Status.ERROR -> {
                        Timber.d(it.message.toString())
                        _dataLoading.postValue(false)
                    }
                }
            }
        }
    }

    private suspend fun getConnections(
        token: String,
        userId: String,
        type: String
    ): Flow<Result<ArrayList<User>>> {
        if (token != "")
            return mainRepository.getAuthenticatedUserConnections(token, type)
        if (userId != "")
            return mainRepository.getUserConnection(userId, type)
        return flow {
            emit(Result.error(msg = "I can't load any User.", data = null))
        }
    }
}