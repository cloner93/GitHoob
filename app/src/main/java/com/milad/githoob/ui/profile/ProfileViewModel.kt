package com.milad.githoob.ui.profile

import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val TAG = "ProfileViewModel@@"
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun loadUserProfile(token: String) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                _user.postValue(mainRepository.getUserInfo(token))
            }
        }
    }
}
