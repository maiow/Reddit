package com.mivanovskaya.humblr.presentation.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mivanovskaya.humblr.data.api.ApiToken
import com.mivanovskaya.humblr.domain.storageservice.StorageService
import com.mivanovskaya.humblr.domain.state.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val apiToken: ApiToken,
    private val storageService: StorageService
) : ViewModel() {

    private val _state = MutableStateFlow<LoadState>(LoadState.NotStartedYet)
    val state = _state.asStateFlow()

    fun createToken(code: String) {
        if (code != PLUG)
            viewModelScope.launch(Dispatchers.IO) {
                _state.value = LoadState.Loading
                try {
                    storageService.saveEncryptedToken(
                        apiToken.getToken(code = code).access_token
                    )
                    _state.value = LoadState.Content()
                } catch (e: Exception) {
                    _state.value = LoadState.Error(message = e.message.toString())
                }
            }
    }

    companion object {
        const val PLUG = ""
    }
}