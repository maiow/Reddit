package com.mivanovskaya.humblr.data.api

import retrofit2.http.*

interface ApiToken {

    @POST("https://www.reddit.com/api/v1/access_token")
    suspend fun getToken(
        @Query("grant_type") grantType: String = "authorization_code",
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri: String = REDIRECT_URI
    ): ResponseToken
}

class ResponseToken(
    val access_token: String
)