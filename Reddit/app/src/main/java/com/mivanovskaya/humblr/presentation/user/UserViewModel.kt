package com.mivanovskaya.humblr.presentation.user

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.mivanovskaya.humblr.domain.repository.ProfileRemoteRepository
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.presentation.profile.ProfileFragmentDirections
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: ProfileRemoteRepository
) : BaseViewModel() {

    fun getProfile(name: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = LoadState.Loading
            _state.value = LoadState.Content(repository.getAnotherUserProfile(name))
        }
    }

    fun makeFriends(name: String, view: View) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.makeFriends(name)
        }
        Snackbar.make(view, "You are friends now", LENGTH_SHORT).show()
    }

    fun navigateToFriends(fragment: Fragment) {
        fragment.findNavController().navigate(
            ProfileFragmentDirections
                .actionNavigationProfileToNavigationFriends()
        )
    }
}