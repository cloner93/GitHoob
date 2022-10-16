package com.milad.githoob.ui.profile.repositories

import android.content.Context
import androidx.lifecycle.*
import com.milad.data.MainRepository
import com.milad.model.event.Repo
import com.milad.githoob.utils.JsonUtils
import com.milad.data.utils.Result
import com.milad.data.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileRepositoriesViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var _repoList = MutableLiveData<ArrayList<Repo>>()
    val repoList: LiveData<ArrayList<Repo>> = _repoList.switchMap {
        switchMatData(it)
    }

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private fun switchMatData(list: ArrayList<Repo>): LiveData<ArrayList<Repo>> {
        val d = MutableLiveData<ArrayList<Repo>>()
        val jsonUser = JsonUtils(context)

        list.map {
            it.language_color = jsonUser.getColor(it.language)
        }

        d.value = list
        return d
    }

    fun setUser(token: String? = "", userId: String? = "") {
        getRepositories(token, userId)
    }

    private fun getRepositories(token: String?, userId: String?) {
        viewModelScope.launch(ioDispatcher) {
            getRepository(token, userId).collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        _repoList.postValue(it.data!!)
                        _dataLoading.postValue(false)
                    }
                    Status.LOADING -> {
                        _dataLoading.postValue(true)
                    }
                    Status.ERROR -> {
                        Timber.d(it.message.toString())
                        _dataLoading.postValue(false)
                    }
                }
            }
        }
    }

    private suspend fun getRepository(
        token: String?,
        userId: String?
    ): Flow<Result<ArrayList<Repo>>> {
        if (token != null&& token != "")
            return mainRepository.getAuthenticatedRepositories(token, 1)
        if (userId != null && userId != "")
            return mainRepository.getUserRepositories(userId, 1)
        return flow {
            emit(Result.error(msg = "I can't load any repo.", data = null))
        }
    }
}