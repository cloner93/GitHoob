package com.milad.githoob.ui.profile

import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.model.User
import com.milad.githoob.utils.AppConstants
import com.milad.githoob.utils.Result
import com.milad.githoob.utils.Status
import com.milad.githoob.utils.contributions.ContributionsDay
import com.milad.githoob.utils.contributions.ContributionsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private lateinit var userId: String
    private lateinit var token: String

    private val _forceUpdate = MutableLiveData(false)
    private val _dataLoading = MutableLiveData(true)
    private val _user = Transformations.switchMap(_forceUpdate) { bool ->
        val user = MutableLiveData<User>()
        if (bool) {
            viewModelScope.launch(ioDispatcher) {

                getUserInfo(token, userId).collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _dataLoading.postValue(false)
                            user.postValue(it.data)
                        }
                        Status.LOADING -> {
                            _dataLoading.postValue(true)
                        }
                        Status.ERROR -> {
                            _dataLoading.postValue(false)
                            Timber.d(it.message.toString())
                        }
                    }
                }
            }
        } else {
            _dataLoading.postValue(false)
        }
        return@switchMap user
    }
    private val _markdown = Transformations.switchMap(_user) {
        val readme = MutableLiveData<String>("")
        viewModelScope.launch(ioDispatcher) {

            val url = "https://raw.githubusercontent.com/${it.login}/${it.login}/master/README.md"

            mainRepository.getUserReadMe(url).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val data = it.data!!.string()
                        readme.postValue(data)
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
        return@switchMap readme
    }

    val dataLoading: LiveData<Boolean> = _dataLoading
    val user: LiveData<User?> = _user
    val userContributes: LiveData<List<ContributionsDay>> = Transformations.switchMap(_user) {
        val list = MutableLiveData<List<ContributionsDay>>()

        val url = String.format(AppConstants.CONTRIBUTE_URL, it.login)

        viewModelScope.launch(ioDispatcher) {
            mainRepository.getUserContribute(url).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        val listCont = ContributionsProvider().getContributions(it.data?.string())
                        list.postValue(listCont)
                    }
                    Status.LOADING -> {
                    }
                    Status.ERROR -> {
                    }
                }
            }
        }

        return@switchMap list
    }
    val markdown: LiveData<String> = _markdown

    fun setUser(token: String, userId: String) {
        this.token = token
        this.userId = userId
        _forceUpdate.value = true
    }

    private suspend fun getUserInfo(token: String, userId: String): Flow<Result<User>> {
        if (token != "")
            return mainRepository.getAuthenticatedUser(token)
        if (userId != "")
            return mainRepository.getUser(userId)
        return flow {
            emit(Result.error(msg = "I can't load any user.", data = null))
        }
    }

    fun refresh() {
        _forceUpdate.value = true
    }
}
