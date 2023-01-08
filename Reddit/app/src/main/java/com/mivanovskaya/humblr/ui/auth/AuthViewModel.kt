package com.mivanovskaya.humblr.ui.auth

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mivanovskaya.humblr.data.api.ApiToken
import com.mivanovskaya.humblr.data.state.LoadState
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val apiToken: ApiToken) : BaseViewModel() {

    private val _token = MutableSharedFlow<String>()
    val token = _token.asSharedFlow()

    private var accessToken = PLUG

    fun createToken(code: String) {
        if (code != PLUG && accessToken != START_REQUEST)
            viewModelScope.launch(Dispatchers.IO) {
                _loadState.emit(LoadState.LOADING)
                accessToken = START_REQUEST
                accessToken = try {
                    Log.e(TAG, "code received, trying to get token")
                     apiToken.getToken(code = code).access_token
                } catch (e: Exception) {
                    _loadState.emit(LoadState.ERROR.apply { message = e.message.toString() })
                    PLUG
                }
                Log.e(TAG, "token is $accessToken")
                _token.emit(accessToken)
                _loadState.emit(LoadState.SUCCESS)
            }
    }

    companion object {
        const val PLUG = ""
        const val START_REQUEST = "start_request"
    }
}