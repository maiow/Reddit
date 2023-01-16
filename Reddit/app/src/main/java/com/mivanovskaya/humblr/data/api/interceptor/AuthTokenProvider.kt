package com.mivanovskaya.humblr.data.api.interceptor

import android.content.Context
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_KEY
import com.mivanovskaya.humblr.domain.sharedpreferences.SharedPrefsService

class AuthTokenProvider(private val context: Context) {

    fun getToken() = SharedPrefsService.create(context)
        .getString(TOKEN_SHARED_KEY, "")
}