package com.mivanovskaya.humblr.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mivanovskaya.humblr.data.repository.ProfileRemoteRepositoryImpl
import com.mivanovskaya.humblr.domain.state.FriendsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val repository: ProfileRemoteRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow<FriendsState>(FriendsState.NotStartedYet)
    val state = _state.asStateFlow()

    fun getFriends() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = FriendsState.Loading
                _state.value = FriendsState.Content(repository.getFriends())
            } catch (e: Error) {
                _state.value = FriendsState.Error("$e")
            }
        }
    }
}
