package com.milad.githoob.data

import com.milad.githoob.data.api.ApiService
import com.milad.githoob.utils.AppConstants.URL_access_token
import com.milad.githoob.utils.SafeApiRequest
import okhttp3.ResponseBody
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) : SafeApiRequest() {

    suspend fun getAccessToken(clientId: String, clientSecret: String, code: String) = apiRequest {
        apiService.getAccessToken(URL_access_token, clientId, clientSecret, code)
    }

    suspend fun getUserInfo(token: String) = apiRequest {
        apiService.getUserInfo(token)
    }

    suspend fun getUserContribute(url: String): ResponseBody {
        return apiService.getUserContribute(url)
    }

    suspend fun getEvents(token: String, username: String, page: Int) =
        apiRequest { apiService.getEvents(token, username, page) }

    suspend fun getMyRepositories(token: String, page: Int) =
        apiRequest { apiService.getMyRepositories(token, page) }

    suspend fun getMyEvents(token: String, username: String, page: Int) =
        apiRequest { apiService.getMyEvents(token, username, page) }
}