package com.milad.githoob.ui.profile.stared

import android.content.Context
import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.event.Repo
import com.milad.githoob.utils.JsonUtils
import com.milad.githoob.utils.Result
import com.milad.githoob.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileStaredViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var _starredList = MutableLiveData<ArrayList<Repo>>()
    val starredList: LiveData<ArrayList<Repo>> = _starredList.switchMap {
        getRepoLangColor(it)
    }

    private fun getRepoLangColor(list: ArrayList<Repo>): LiveData<ArrayList<Repo>> {
        val d = MutableLiveData<ArrayList<Repo>>()
        val jsonUser = JsonUtils(context)

        list.map {
            it.language_color = jsonUser.getColor(it.language)
        }

        d.value = list
        return d
    }

    fun setUser(token: String = "", userId: String = "") {
        getStarredRepo(token, userId)
    }

    private fun getStarredRepo(token: String, userId: String) {
        viewModelScope.launch(ioDispatcher) {
            getStarred(token, userId).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        _starredList.postValue((it.data!!))
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

    private suspend fun getStarred(
        token: String,
        userId: String
    ): Flow<Result<ArrayList<Repo>>> {
        if (token != "")
            return mainRepository.getAuthenticatedUserStarred(token)
        if (userId != "")
            return mainRepository.getUserStarred(userId)
        return flow {
            emit(Result.error(msg = "I can't load any repo.", data = null))
        }
    }
}