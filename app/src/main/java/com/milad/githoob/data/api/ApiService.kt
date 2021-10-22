package com.milad.githoob.data.api

import com.milad.githoob.data.model.AccessToken
import com.milad.githoob.data.model.GithubUserModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
    ): AccessToken

    @Headers("Content-Type: application/json")
    @GET("/user")
    suspend fun getUserInfo(
        @Header("Authorization") token: String
    ): Response<GithubUserModel>

}