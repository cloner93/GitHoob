package com.milad.githoob.data

import com.milad.githoob.data.api.ApiService
import com.milad.githoob.utils.AppConstants.URL_access_token
import com.milad.githoob.utils.SafeApiRequest
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) : SafeApiRequest() {

    suspend fun getAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ) = apiRequest {
        apiService.getAccessToken(URL_access_token, clientId, clientSecret, code)
    }

    suspend fun getAuthenticatedUser(token: String) =
        apiRequest { apiService.getAuthenticatedUser(token) }

    suspend fun getUser(username: String) =
        apiRequest { apiService.getUser(username) }

    suspend fun getUserContribute(url: String) = apiRequest { apiService.getUserContribute(url) }

    suspend fun getEvents(token: String, username: String, page: Int) =
        apiRequest { apiService.getEvents(token, username, page) }

    suspend fun getAuthenticatedRepositories(token: String, page: Int) =
        apiRequest { apiService.getAuthenticatedRepositories(token, page) }

    suspend fun getProject(token: String?, owner: String, project: String) =
        apiRequest { apiService.getProject(token, owner, project) }

    suspend fun getProjectContributors(token: String?, owner: String, project: String) =
        apiRequest { apiService.getProjectContributors(token, owner, project) }

    suspend fun getProjectReadMe(token: String?, owner: String, project: String) =
        apiRequest { apiService.getProjectReadMe(token, owner, project) }

    suspend fun getUserReadMe(url: String) =
        apiRequest { apiService.getUserReadMe(url) }

    suspend fun getUserRepositories(token: String, page: Int) =
        apiRequest { apiService.getUserRepositories(token, page) }

    suspend fun getAuthenticatedUserStarred(token: String) =
        apiRequest { apiService.getAuthenticatedUserStarred(token) }

    suspend fun getUserStarred(username: String) =
        apiRequest { apiService.getUserStarred(username) }

    suspend fun getMyEvents(token: String, username: String, page: Int) =
        apiRequest { apiService.getMyEvents(token, username, page) }

    suspend fun getAuthenticatedUserOrgs(token: String) =
        apiRequest { apiService.getAuthenticatedUserOrgs(token) }

    suspend fun getUserOrgs(username: String) =
        apiRequest { apiService.getUserOrgs(username) }

    suspend fun getAuthenticatedUserConnections(token: String, type: String) =
        apiRequest { apiService.getAuthenticatedUserConnections(token, type) }

    suspend fun getUserConnection(username: String, type: String) =
        apiRequest { apiService.getUserConnections(username, type) }
}