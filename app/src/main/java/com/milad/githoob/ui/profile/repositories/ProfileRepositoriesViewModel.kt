package com.milad.githoob.ui.profile.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import com.milad.githoob.data.model.event.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileRepositoriesViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _repoList = MutableLiveData<ArrayList<Repo>>()
    val repoList: LiveData<ArrayList<Repo>> = _repoList

    fun setUser(token: String, user: User) {
        viewModelScope.launch {

            getRepositories(token, 1)
        }
    }

    private fun getRepositories(token: String, page: Int) {
        viewModelScope.launch(ioDispatcher) {
            try {
                _repoList.postValue(mainRepository.getMyRepositories(token, page))
            } catch (e: Exception) {
                e.message?.let { Log.d("Get Repositories", it) }
            }
        }
    }
}