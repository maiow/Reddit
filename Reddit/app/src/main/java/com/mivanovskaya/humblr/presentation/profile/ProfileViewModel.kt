package com.mivanovskaya.humblr.presentation.profile

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_KEY
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
    private val repository: ProfileRemoteRepository,
    private val sharedPrefsService: SharedPrefsService
) : BaseViewModel() {

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = LoadState.Loading
            _state.value = LoadState.Content(repository.getLoggedUserProfile())
        }
    }

    fun getClearedUrlAvatar(urlAvatar: String): String {
        val questionMark = urlAvatar.indexOf('?', 0)
        return urlAvatar.substring(0, questionMark)
    }

    fun logout(context: Context, fragment: Fragment) {
        sharedPrefsService.save(context, TOKEN_SHARED_KEY, "")
        sharedPrefsService.save(context, TOKEN_ENABLED_KEY, false)
        fragment.findNavController()
            .navigate(ProfileFragmentDirections.actionNavigationProfileToNavigationAuth())
    }

    fun navigateToFriends(fragment: Fragment) {
        fragment.findNavController().navigate(
            ProfileFragmentDirections
                .actionNavigationProfileToNavigationFriends()
        )
    }

    fun clearSaved() {
        viewModelScope.launch(Dispatchers.IO + handler) {
           TODO()
           // repository.clearSaved()
        }
    }
}