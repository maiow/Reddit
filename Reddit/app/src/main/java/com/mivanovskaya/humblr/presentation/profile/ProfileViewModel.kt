package com.mivanovskaya.humblr.presentation.profile

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_NAME
import com.mivanovskaya.humblr.data.repository.ProfileRemoteRepositoryImpl
import com.mivanovskaya.humblr.domain.sharedpreferences.SharedPrefsService
import com.mivanovskaya.humblr.domain.state.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRemoteRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.NotStartedYet)
    val state = _state.asStateFlow()

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = ProfileState.Loading
                _state.value = ProfileState.Content(repository.getProfile())
            } catch (e: Error) {
                _state.value = ProfileState.Error("$e")
            }
        }
    }
    fun getClearedUrlAvatar(urlAvatar: String): String {
        val questionMark = urlAvatar.indexOf('?', 0)
        return urlAvatar.substring(0, questionMark)
    }

    fun logout(context: Context, fragment: Fragment) {
        val preferences = SharedPrefsService.create(context, TOKEN_SHARED_NAME)
        preferences.edit().putString(TOKEN_SHARED_KEY, "").apply()
        preferences.edit().putBoolean(TOKEN_ENABLED_KEY, false).apply()
        fragment.findNavController()
            .navigate(ProfileFragmentDirections.actionNavigationProfileToNavigationAuth())
    }
}