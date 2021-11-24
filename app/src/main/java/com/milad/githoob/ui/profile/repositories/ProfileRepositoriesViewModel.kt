package com.milad.githoob.ui.profile.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import com.milad.githoob.data.model.event.Repo
import com.milad.githoob.utils.JsonUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.ArrayList

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

        d.postValue(list)
        return d
    }

    fun setUser(token: String, user: User) {
        viewModelScope.launch {

            getRepositories(token, 1)
        }
    }

    private fun getRepositories(token: String, page: Int) {
        viewModelScope.launch(ioDispatcher) {
            try {
                _repoList.postValue(mainRepository.getMyRepositories(token, page))
            } catch (e: Exception) {
                e.message?.let { Log.d("Get Repositories", it) }
            }
        }
    }
}