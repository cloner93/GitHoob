package com.milad.githoob.ui.profile

import android.util.Log
import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.event.Events
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

    private val token = MutableLiveData<String>()
    private lateinit var tokenTemp :String

    fun setToken(token: String) {
        this.token.postValue(token)
        this.tokenTemp= token
    }

    private val _user = token.switchMap { token ->
        val user = MutableLiveData<User>()
        _dataLoading.postValue(true)

        viewModelScope.launch {
            val userInfo = mainRepository.getUserInfo(token)
            user.value = (userInfo)

            loadUserContribute(userInfo.login)
            // TODO: 11/1/2021 Paging library most be handle soon.
            getFeeds(token, userInfo.login, 0)


            _dataLoading.postValue(false)
        }
        return@switchMap user
    }
    val user: LiveData<User?> = _user

    private val _userContributes = MutableLiveData<List<ContributionsDay>>()
    val userContributes: LiveData<List<ContributionsDay>> = _userContributes

    private var _feedsList = MutableLiveData<ArrayList<Events>>()
    val feedsList: LiveData<ArrayList<Events>> = _feedsList

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private suspend fun loadUserContribute(id: String) {
        val url = String.format(AppConstants.CONTRIBUTE_URL, id)

        _userContributes.postValue(
            ContributionsProvider().getContributions(
                mainRepository.getUserContribute(url).string()
            )
        )

    }

    private suspend fun getFeeds(token: String, username: String, page: Int) {
        try {
            _feedsList.postValue(
                mainRepository.getEvents(
                    token,
                    username,
                    page
                )
            )
        } catch (e: Exception) {
            e.message?.let { Log.d("Get Feeds", it) }
        }
    }

    fun getUserProfile(event: Events) {
        Log.d(TAG, "getUserProfile: ${event.actor.login}")
    }

    fun refresh() {
        _forceUpdate.value = true
        token.postValue(tokenTemp)
    }
}
