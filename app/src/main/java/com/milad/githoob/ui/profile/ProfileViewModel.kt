package com.milad.githoob.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milad.githoob.data.MainRepository
import com.milad.githoob.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _user = MutableLiveData<User?>()

    init {
        _user.value =
            User(
                login = "Cloner93",
                id = 1,
                bio = "I AM FU***NG ANDROID DEVELOPER",
                followers = 1000000,
                following = 10,
                public_repos = 10000,
                avatar_url = "https://avatars.githubusercontent.com/u/14924296?s=400&u=d97d3d4bce8685d865573543abc71a75b27dde51&v=4"
            )

    }

    val user: LiveData<User?> = _user
}
