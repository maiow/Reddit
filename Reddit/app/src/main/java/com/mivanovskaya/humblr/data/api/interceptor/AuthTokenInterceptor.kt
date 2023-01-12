package com.mivanovskaya.humblr.data.api.interceptor

import android.util.Base64
import android.util.Log
import com.mivanovskaya.humblr.data.api.CLIENT_ID
import com.mivanovskaya.humblr.data.api.CLIENT_SECRET
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(private val tokenProvider: AuthTokenProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        Log.e("url: ", chain.request().url.encodedPath)
        if (chain.request().url.encodedPath == "/api/v1/access_token") {
            request = request.newBuilder().addHeader("Authorization", "Basic $encodedAuthString").build()
            return chain.proceed(request)
        }
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${tokenProvider.getToken()}")
            .addHeader( "User-Agent", "android:com.humblr:v1.0 by Beginning_Android")
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val authString = "$CLIENT_ID:$CLIENT_SECRET"
        val encodedAuthString: String =
            Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)

    }
}