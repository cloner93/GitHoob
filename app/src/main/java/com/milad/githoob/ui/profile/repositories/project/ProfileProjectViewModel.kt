package com.milad.githoob.ui.profile.repositories.project

import android.util.Log
import androidx.lifecycle.*
import com.milad.data.utils.Status
import com.milad.githoob.utils.GlobalState.TAG
import com.milad.model.event.Contributor
import com.milad.model.event.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileProjectViewModel @Inject constructor(
    private val mainRepository: com.milad.data.MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private lateinit var userId: String
    private val _repo = MutableLiveData<Repo>()
    val repo: LiveData<Repo> = _repo

    private val _contributors = MutableLiveData<List<Contributor>>()
    val contributors: LiveData<List<Contributor>> = Transformations.switchMap(_contributors) {
        it.forEach { item -> Log.d(TAG, item.toString()) }
        val readme = MutableLiveData<List<Contributor>>()
         if (it.size == 1 && it.first().login == userId){
            readme.postValue(emptyList<Contributor>())
        }else
             readme.postValue(it)

        return@switchMap readme
    }

    private val _markdown = MutableLiveData<String>("")
    val markdown: LiveData<String> = _markdown

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun setUser(token: String?, userId: String, projectName: String) {
        this.userId = userId;
        loadProjectData(token, userId, projectName)
    }

    private fun loadProjectData(token: String?, userId: String, projectName: String) {
        viewModelScope.launch(ioDispatcher) {
            getProjectData(token, userId, projectName)
            getContributor(token, userId, projectName)
            getMarkdown(token, userId, projectName)
        }
    }

    private suspend fun getMarkdown(token: String?, userId: String, projectName: String) {
        mainRepository.getProjectReadMe(token, userId, projectName).collectLatest {
            when (it.status) {
                Status.SUCCESS -> {
                    val data = it.data!!.string()
                    _markdown.postValue(data)
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

    private suspend fun getProjectData(token: String?, userId: String, projectName: String) {
        mainRepository.getProject(token, userId, projectName).collectLatest {
            when (it.status) {
                com.milad.data.utils.Status.SUCCESS -> {
                    _repo.postValue(it.data!!)
                    _dataLoading.postValue(false)
                }
                com.milad.data.utils.Status.LOADING -> {
                    _dataLoading.postValue(true)
                }
                com.milad.data.utils.Status.ERROR -> {
                    Timber.d(it.message.toString())
                    _dataLoading.postValue(false)
                }
            }
        }
    }

    private suspend fun getContributor(token: String?, userId: String, projectName: String) {
        mainRepository.getProjectContributors(token, userId, projectName).collectLatest {
            when (it.status) {
                com.milad.data.utils.Status.SUCCESS -> {
                    _contributors.postValue(it.data!!)
                }
                com.milad.data.utils.Status.LOADING -> {
                    // TODO: 2/1/2022 set loading
                }
                com.milad.data.utils.Status.ERROR -> {
                    Timber.d(it.message.toString())
                }
            }
        }
    }
}