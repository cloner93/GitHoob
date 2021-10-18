package com.milad.githoob.data

import com.milad.githoob.data.api.ApiService
import com.milad.githoob.data.model.AccessToken
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): AccessToken {
        return apiService.getAccessToken(clientId, clientSecret, code)
    }
}