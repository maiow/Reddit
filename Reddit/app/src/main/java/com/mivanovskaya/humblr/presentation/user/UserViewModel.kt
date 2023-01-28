package com.mivanovskaya.humblr.presentation.user

import androidx.lifecycle.viewModelScope
import com.mivanovskaya.humblr.domain.repository.ProfileRemoteRepository
import com.mivanovskaya.humblr.domain.repository.SubredditsRemoteRepository
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repositoryProfile: ProfileRemoteRepository,
    private val repositorySubreddits: SubredditsRemoteRepository
) : BaseViewModel() {

    fun getProfileAndContent(name: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = LoadState.Loading
            _state.value = LoadState.Content(
                repositoryProfile.getAnotherUserProfile(name),
                repositoryProfile.getUserContent(name)
            )
        }
    }

    fun makeFriends(name: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repositoryProfile.makeFriends(name)
        }
    }

    fun votePost(voteDirection: Int, postName: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repositorySubreddits.votePost(voteDirection, postName)
        }
    }

    fun savePost(postName: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repositorySubreddits.savePost(postName)
        }
    }

    fun unsavePost(postName: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repositorySubreddits.unsavePost(postName)
        }
    }
}