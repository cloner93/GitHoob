package com.milad.githoob.ui.profile.repositories.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.event.Contributor
import com.milad.githoob.data.model.event.Repo
import com.milad.githoob.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileProjectViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _repo = MutableLiveData<Repo>()
    val repo: LiveData<Repo> = _repo

    private val _contributors = MutableLiveData<List<Contributor>>()
    val contributors: LiveData<List<Contributor>> = _contributors

    fun setUser(token: String?, userId: String, projectName: String) =
        loadProjectData(token, userId, projectName)

    private fun loadProjectData(token: String?, userId: String, projectName: String) {
        viewModelScope.launch(ioDispatcher) {
            getProjectData(token, userId, projectName)
            getContributor(token, userId, projectName)
        }
    }

    private suspend fun getProjectData(token: String?, userId: String, projectName: String) {
        mainRepository.getProject(token, userId, projectName).collect {
            when (it.status) {
                Status.SUCCESS -> {
                    _repo.postValue(it.data!!)
                }
                Status.LOADING -> {
                    // TODO: 2/1/2022 set loading
                }
                Status.ERROR -> {
                    Timber.d(it.message.toString())
                }
            }
        }
    }

    private suspend fun getContributor(token: String?, userId: String, projectName: String) {
        mainRepository.getProjectContributors(token, userId, projectName).collect {
            when (it.status) {
                Status.SUCCESS -> {
                    _contributors.postValue(it.data!!)
                }
                Status.LOADING -> {
                    // TODO: 2/1/2022 set loading
                }
                Status.ERROR -> {
                    Timber.d(it.message.toString())
                }
            }
        }
    }
}