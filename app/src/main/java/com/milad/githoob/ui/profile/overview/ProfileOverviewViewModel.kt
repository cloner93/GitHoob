package com.milad.githoob.ui.profile.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import com.milad.githoob.data.model.event.Event
import com.milad.githoob.utils.AppConstants
import com.milad.githoob.utils.Status
import com.milad.githoob.utils.contributions.ContributionsDay
import com.milad.githoob.utils.contributions.ContributionsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileOverviewViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _userContributes = MutableLiveData<List<ContributionsDay>>()
    val userContributes: LiveData<List<ContributionsDay>> = _userContributes

    private var _feedsList = MutableLiveData<ArrayList<Event>>()
    val feedsList: LiveData<ArrayList<Event>> = _feedsList

    fun setUser(token: String, user: User) {
        viewModelScope.launch {
            loadUserContribute(user.login)

            // TODO: 11/1/2021 Paging library most be handle soon.
            getFeeds(token, user.login, 0)
        }
    }

    private suspend fun loadUserContribute(id: String) {
        val url = String.format(AppConstants.CONTRIBUTE_URL, id)
        withContext(ioDispatcher) {
            _userContributes.postValue(
                ContributionsProvider().getContributions(
                    mainRepository.getUserContribute(url).string()
                )
            )
        }
    }

    private suspend fun getFeeds(token: String, username: String, page: Int) {
        viewModelScope.launch(ioDispatcher) {
            mainRepository.getEvents(
                token,
                username,
                page
            ).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        _feedsList.postValue(it.data!!)
                    }
                    Status.LOADING -> {}
                    Status.ERROR -> {
                        Timber.d(it.message)
                    }
                }
            }
        }
    }

    fun getUserProfile(event: Event) {
        Timber.d("getUserProfile: " + event.actor.login)
    }
}
