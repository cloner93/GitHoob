package com.milad.githoob.data

import com.milad.githoob.data.api.ApiService
import com.milad.githoob.utils.AppConstants.URL_access_token
import com.milad.githoob.utils.SafeApiRequest
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) : SafeApiRequest() {

    suspend fun getAccessToken(clientId: String, clientSecret: String, code: String) =
        apiRequest {
            apiService.getAccessToken(URL_access_token, clientId, clientSecret, code)
        }

    suspend fun getUserInfo(token: String) = apiRequest {
        apiService.getUserInfo(token)
    }
}