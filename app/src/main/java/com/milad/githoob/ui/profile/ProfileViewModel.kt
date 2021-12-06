package com.milad.githoob.ui.profile

import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val TAG = "ProfileViewModel@@"

    private lateinit var token: String

    // TODO: setValue() method must be called from the main thread. But if you need set a value from a background thread, postValue() should be used.

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _user = _forceUpdate.switchMap { bool ->
        val user = MutableLiveData<User>()
        if (bool) {
            _dataLoading.postValue(true)
            viewModelScope.launch {
                val userInfo = mainRepository.getUserInfo(token)
                user.value = (userInfo)

                _dataLoading.postValue(false)
            }
        }
        return@switchMap user
    }
    val user: LiveData<User?> = _user

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun setToken(token: String) {
        this.token = token
        _forceUpdate.postValue(true)
    }

    fun refresh() {
        _forceUpdate.value = true
    }
}
