package com.milad.githoob.data.api

import com.milad.githoob.data.model.AccessToken
import com.milad.githoob.data.model.User
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

}