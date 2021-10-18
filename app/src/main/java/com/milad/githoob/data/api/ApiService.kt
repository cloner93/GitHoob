package com.milad.githoob.data.api

import com.milad.githoob.data.model.AccessToken
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
    ): AccessToken

}