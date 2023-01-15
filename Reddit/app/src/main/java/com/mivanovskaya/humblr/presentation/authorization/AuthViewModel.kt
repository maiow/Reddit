package com.mivanovskaya.humblr.presentation.authorization

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mivanovskaya.humblr.data.api.*
import com.mivanovskaya.humblr.domain.sharedpreferences.SharedPrefsService
import com.mivanovskaya.humblr.domain.state.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val apiToken: ApiToken) : ViewModel() {

    private val _state = MutableStateFlow<LoadState>(LoadState.NotStartedYet)
    val state = _state.asStateFlow()

    fun createToken(code: String, context: Context) {
        if (code != PLUG)
            viewModelScope.launch(Dispatchers.IO) {
                _state.value = LoadState.Loading
                try {
                    SharedPrefsService.saveEncryptedToken(
                        context,
                        apiToken.getToken(code = code).access_token
                    )
                    _state.value = LoadState.Content(data = null)
                } catch (e: Exception) {
                    _state.value = LoadState.Error(message = e.message.toString())
                }
            }
    }

    companion object {
        const val PLUG = ""
    }
}