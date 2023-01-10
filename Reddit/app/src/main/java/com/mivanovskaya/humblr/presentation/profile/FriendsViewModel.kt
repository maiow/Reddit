package com.mivanovskaya.humblr.presentation.profile

import androidx.lifecycle.viewModelScope
import com.mivanovskaya.humblr.data.repository.ProfileRemoteRepositoryImpl
import com.mivanovskaya.humblr.domain.models.FriendsState
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val repository: ProfileRemoteRepositoryImpl
) : BaseViewModel() {

    private val _state = MutableStateFlow<FriendsState>(FriendsState.NotStartedYet)
    val state = _state.asStateFlow()

    fun getFriends() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = FriendsState.Loading
            _state.value = FriendsState.Success(repository.getFriends())
        }
    }
}
