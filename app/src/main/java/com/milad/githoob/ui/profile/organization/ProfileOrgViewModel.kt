package com.milad.githoob.ui.profile.organization

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.event.Repo
import com.milad.githoob.utils.Result
import com.milad.githoob.utils.Status
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ProfileOrgViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) : ViewModel() {


    fun setUser(token: String?, userId: String?) {
       // getStarredRepo(token, userId) // FIXME:
    }
/*
    private fun getStarredRepo(token: String?, userId: String?) {
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
        token: String?,
        userId: String?
    ): Flow<Result<ArrayList<Repo>>> {
        if (token != null && token != "")
            return mainRepository.getAuthenticatedUserStarred(token)
        if (userId != null && userId != "")
            return mainRepository.getUserStarred(userId)
        return flow {
            emit(Result.error(msg = "I can't load any repo.", data = null))
        }
    }

 */
}