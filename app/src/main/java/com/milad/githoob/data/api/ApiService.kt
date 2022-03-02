package com.milad.githoob.data.api

import com.milad.githoob.data.model.AccessToken
import com.milad.githoob.data.model.Org
import com.milad.githoob.data.model.User
import com.milad.githoob.data.model.event.Event
import com.milad.githoob.data.model.event.Repo
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/json")
    @POST
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Url url: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
    ): Response<AccessToken>

    @Headers("Content-Type: application/json")
    @GET("/user")
    suspend fun getAuthenticatedUser(
        @Header("Authorization") token: String
    ): Response<User>

    @Headers("Content-Type: application/json")
    @GET("/user/starred")
    suspend fun getAuthenticatedUserStarred(
        @Header("Authorization") token: String
    ): Response<ArrayList<Repo>>

    @Headers("Content-Type: application/json")
    @GET("/users/{username}/starred")
    suspend fun getUserStarred(
        @Path("username") username: String
    ): Response<ArrayList<Repo>>

    @GET("/user/orgs")
    suspend fun getAuthenticatedUserOrgs(
        @Header("Authorization") token: String
    ): Response<ArrayList<Org>>

    @GET("/users/{username}/orgs")
    suspend fun getUserOrgs(
        @Path("username") username: String
    ): Response<ArrayList<Org>>

    @Headers("Content-Type: application/json")
    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): Response<User>

    @Headers("Content-Type: application/json")
    @GET("/user/repos?sort=updated&per_page=100")
    suspend fun getAuthenticatedRepositories(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): Response<ArrayList<Repo>>

    @Headers("Content-Type: application/json")
    @GET("/repos/{owner}/{repo}")
    suspend fun getProject(
        @Header("Authorization") token: String?,
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<Repo>

    @Headers("Content-Type: application/json")
    @GET("/users/{username}/repos?sort=updated")
    suspend fun getUserRepositories(
        @Path("username") username: String,
        @Query("page") page: Int
    ): Response<ArrayList<Repo>>

    @GET
    suspend fun getUserContribute(@Url url: String): Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("/users/{username}/received_events?per_page=100")
    suspend fun getEvents(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Query("page") page: Int
    ): Response<ArrayList<Event>>

    @Headers("Content-Type: application/json")
        @GET("/users/{username}/events")
    suspend fun getMyEvents(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Query("page") page: Int
    ): Response<ArrayList<Event>>

}