package com.milad.githoob.ui.profile.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import com.milad.githoob.data.model.event.Events
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFeedsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _eventsList = MutableLiveData<ArrayList<Events>>()
    val eventsList: LiveData<ArrayList<Events>> = _eventsList

    fun setUser(token: String, user: User) {

        viewModelScope.launch(ioDispatcher) {
            try {
                _eventsList.postValue(
                    mainRepository.getMyEvents(token, user.login, 1)
                )
            } catch (ex: Exception) {
                ex.message?.let { Log.d("Get events", it) }
            }
        }

    }
}