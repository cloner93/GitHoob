package com.milad.githoob.ui.profile

import android.util.Log
import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import com.milad.githoob.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private lateinit var token: String

    // TODO: setValue() method must be called from the main thread. But if you need set a value from a background thread, postValue() should be used.

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _user = _forceUpdate.switchMap { bool ->
        val user = MutableLiveData<User>()
        if (bool) {
            _dataLoading.postValue(true)
            viewModelScope.launch(ioDispatcher) {

                mainRepository.getUserInfo(token).collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _dataLoading.postValue(false)
                            user.postValue(it.data)
                        }
                        Status.LOADING -> {
                            _dataLoading.postValue(false)
                        }
                        Status.ERROR -> {
                            _dataLoading.postValue(false)
                            Timber.d(it.message.toString())
                        }
                    }
                }
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
