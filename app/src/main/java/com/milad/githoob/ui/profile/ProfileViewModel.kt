package com.milad.githoob.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import com.milad.githoob.utils.AppConstants
import com.milad.githoob.utils.contributions.ContributionsDay
import com.milad.githoob.utils.contributions.ContributionsProvider
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

    private val _userContributes = MutableLiveData<List<ContributionsDay>>()
    val userContributes: LiveData<List<ContributionsDay>> = _userContributes

    fun loadUserProfile(token: String) {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                val userInfo = mainRepository.getUserInfo(token)
                _user.postValue(userInfo)
                loadUserContribute(userInfo.login)
            }
        }
    }

    private suspend fun loadUserContribute(id: String) {
        val url = String.format(AppConstants.CONTRIBUTE_URL, id)

        _userContributes.postValue(
            ContributionsProvider().getContributions(
                mainRepository.getUserContribute(url).string()
            )
        )

    }
}
