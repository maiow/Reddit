package com.mivanovskaya.humblr.presentation.profile

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_NAME
import com.mivanovskaya.humblr.domain.repository.ProfileRemoteRepository
import com.mivanovskaya.humblr.domain.sharedpreferences.SharedPrefsService
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRemoteRepository
) : BaseViewModel() {

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = LoadState.Loading
            _state.value = LoadState.Content(repository.getProfile())
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

    fun navigateToFriends(userId: String, fragment: Fragment) {
        fragment.findNavController().navigate(
            ProfileFragmentDirections
                .actionNavigationProfileToNavigationFriends(userId)
        )
    }
}