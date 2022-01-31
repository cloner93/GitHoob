package com.milad.githoob.ui.profile.repositories

import android.content.Context
import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import com.milad.githoob.data.model.event.Repo
import com.milad.githoob.utils.JsonUtils
import com.milad.githoob.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
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

    private fun switchMatData(list: ArrayList<Repo>): LiveData<ArrayList<Repo>> {
        val d = MutableLiveData<ArrayList<Repo>>()
        val jsonUser = JsonUtils(context)

        list.map {
            it.language_color = jsonUser.getColor(it.language)
        }

        d.value = list
        return d
    }

    fun setUser(token: String, user: User?) {
        getRepositories(token, 1)
    }

    private fun getRepositories(token: String, page: Int) {
        viewModelScope.launch(ioDispatcher) {
            mainRepository.getMyRepositories(token, page).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        _repoList.postValue(it.data!!)
                    }
                    Status.LOADING -> {}
                    Status.ERROR -> {
                        Timber.d(it.message.toString())
                    }
                }
            }
        }
    }
}