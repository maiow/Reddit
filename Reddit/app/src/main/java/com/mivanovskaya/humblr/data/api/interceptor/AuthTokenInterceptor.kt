package com.mivanovskaya.humblr.data.api.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(private val tokenProvider: AuthTokenProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e("Kart", "интерцептор токен = ${tokenProvider.getToken()}")
        val request = chain.request()
        if (chain.request().url.encodedPath != "/api/v1/access_token") {
            return chain.proceed(request)
        }
        val newRequest = request.newBuilder()
//            .addHeader("Authorization", "Bearer ${tokenProvider.getToken()}")
            .build()
        return chain.proceed(newRequest)
    }
}