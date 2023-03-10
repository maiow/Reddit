package com.mivanovskaya.humblr.presentation.profile

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_KEY
import com.mivanovskaya.humblr.domain.repository.ProfileRemoteRepository
import com.mivanovskaya.humblr.domain.repository.SubredditsRemoteRepository
import com.mivanovskaya.humblr.domain.sharedpreferences.SharedPrefsService
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repositoryProfile: ProfileRemoteRepository,
    private val repositorySubreddits: SubredditsRemoteRepository,
    private val sharedPrefsService: SharedPrefsService
) : BaseViewModel() {

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = LoadState.Loading
            _state.value = LoadState.Content(repositoryProfile.getLoggedUserProfile())
        }
    }

    fun getClearedUrlAvatar(urlAvatar: String): String {
        var clearedUrl = urlAvatar
        if (urlAvatar.contains('?')) {
            val questionMark = urlAvatar.indexOf('?', 0)
            clearedUrl = urlAvatar.substring(0, questionMark)
        }
        return clearedUrl
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
            repositorySubreddits.unsaveAllSavedPosts()
        }
    }
}