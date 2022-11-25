package com.milad.githoob.ui.profile.organization

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milad.data.MainRepository
import com.milad.data.utils.Status
import com.milad.data.utils.Result
import com.milad.model.Org
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
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

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun setUser(token: String, userId: String) {
        getOrgs(token, userId)
    }

    private fun getOrgs(token: String, userId: String) {
        viewModelScope.launch(ioDispatcher) {
            getOrgsFlow(token, userId).collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        Log.d("@@", "getOrgs: ${it.data?.size}")
                        _orgsList.postValue((it.data!!))
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

    private suspend fun getOrgsFlow(
        token: String,
        userId: String
    ): Flow<Result<ArrayList<Org>>> {
        if (token != "")
            return mainRepository.getAuthenticatedUserOrgs(token)
        if (userId != "")
            return mainRepository.getUserOrgs(userId)
        return flow {
            emit(Result.error(msg = "I can't load any repo.", data = null))
        }
    }

}