package com.milad.githoob.data.api

import com.milad.githoob.data.model.event.Events
import com.milad.githoob.data.model.AccessToken
import com.milad.githoob.data.model.User
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
    suspend fun getUserInfo(
        @Header("Authorization") token: String
    ): Response<User>

    @GET
    suspend fun getUserContribute(@Url url: String) :ResponseBody

    @Headers("Content-Type: application/json")
    @GET("/users/{username}/received_events?per_page=100")
    suspend fun getEvents(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Query("page") page: Int
    ): Response<ArrayList<Events>>

    @Headers("Content-Type: application/json")
    @GET("/user/repos?sort=updated&per_page=100")
    suspend fun getMyRepositories(
        @Header("Authorization") user: String,
        @Query("page") page: Int
    ): Response<ArrayList<Repo>>
}