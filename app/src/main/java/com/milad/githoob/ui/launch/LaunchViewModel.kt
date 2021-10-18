package com.milad.githoob.ui.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.milad.githoob.data.MainRepository
import com.milad.githoob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    fun fetchToken(
        clientId: String,
        clientSecret: String,
        code: String
    ) = liveData(ioDispatcher) {
        emit(Resource.loading(null))
        try {
            emit(
                Resource.success(
                    data = mainRepository.getAccessToken(
                        clientId,
                        clientSecret,
                        code
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(exception.message ?: "Error Occurred!", data = null))
        }
    }
}