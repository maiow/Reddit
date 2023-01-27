package com.mivanovskaya.humblr.presentation.user

import androidx.lifecycle.viewModelScope
import com.mivanovskaya.humblr.domain.repository.ProfileRemoteRepository
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: ProfileRemoteRepository
) : BaseViewModel() {

    fun getProfileAndContent(name: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = LoadState.Loading
            //TODO: change for 2 datas in 1 LoadState.Content maybe?
            _state.value = LoadState.Content(repository.getAnotherUserProfile(name))
            _state.value = LoadState.Content2(repository.getUserContent(name))
        }
    }

    fun makeFriends(name: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.makeFriends(name)
        }
    }
}