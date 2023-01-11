package com.mivanovskaya.humblr.data.api

import android.util.Base64
import retrofit2.http.*

interface ApiToken {

    @POST("https://www.reddit.com/api/v1/access_token")
    suspend fun getToken(
        @Header("Authorization") authString: String = "Basic $encodedAuthString",
        @Query("grant_type") grantType: String = "authorization_code",
        @Query("code") code: String,
        @Query("redirect_uri") redirectUri: String = REDIRECT_URI
    ): ResponseToken

    companion object {
        private const val authString = "$CLIENT_ID:$CLIENT_SECRET"
        val encodedAuthString: String =
            Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)

    }
}

class ResponseToken(
    val access_token: String
)