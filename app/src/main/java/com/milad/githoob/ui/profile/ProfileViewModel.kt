package com.milad.githoob.ui.profile

import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import com.milad.githoob.utils.AppConstants
import com.milad.githoob.utils.Status
import com.milad.githoob.utils.contributions.ContributionsDay
import com.milad.githoob.utils.contributions.ContributionsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private lateinit var token: String

    private val _forceUpdate = MutableLiveData(false)
    private val _user = _forceUpdate.switchMap { bool ->
        val user = MutableLiveData<User>()
        if (bool) {
            _dataLoading.postValue(true)
            viewModelScope.launch(ioDispatcher) {

                mainRepository.getUserInfo(token).collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _dataLoading.postValue(false)
                            user.postValue(it.data)
                        }
                        Status.LOADING -> {
                            _dataLoading.postValue(false)
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

    val userContributes: LiveData<List<ContributionsDay>> = Transformations.switchMap(_user) {
        val list = MutableLiveData<List<ContributionsDay>>()

        _dataLoading.postValue(true)
        val url = String.format(AppConstants.CONTRIBUTE_URL, it.login)

        viewModelScope.launch(ioDispatcher) {
            list.postValue(
                ContributionsProvider().getContributions(

                    mainRepository.getUserContribute(
                        url
                    ).string()
                )
            )
        }

        return@switchMap list
    }

    val user: LiveData<User?> = _user

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun refresh(token: String) {
        this.token = token
        _forceUpdate.postValue(true)
    }
}
