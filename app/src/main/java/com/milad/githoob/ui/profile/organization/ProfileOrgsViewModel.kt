package com.milad.githoob.ui.profile.organization

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.Org
import com.milad.githoob.utils.Result
import com.milad.githoob.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileOrgsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _orgsList: MutableLiveData<ArrayList<Org>> = MutableLiveData()
    val orgsList: LiveData<ArrayList<Org>> = _orgsList

    fun setUser(token: String?, userId: String?) {
        getOrgs(token, userId)
    }

    private fun getOrgs(token: String?, userId: String?) {
        viewModelScope.launch(ioDispatcher) {
            getOrgsFlow(token, userId).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        Log.d("@@", "getOrgs: ${it.data?.size}")
                        _orgsList.postValue((it.data!!))
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

    private suspend fun getOrgsFlow(
        token: String?,
        userId: String?
    ): Flow<Result<ArrayList<Org>>> {
        if (token != null && token != "")
            return mainRepository.getAuthenticatedUserOrgs(token)
        if (userId != null && userId != "")
            return mainRepository.getUserOrgs(userId)
        return flow {
            emit(Result.error(msg = "I can't load any repo.", data = null))
        }
    }

}