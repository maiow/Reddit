package com.mivanovskaya.humblr.presentation.profile

import androidx.lifecycle.viewModelScope
import com.mivanovskaya.humblr.data.repository.ProfileRemoteRepositoryImpl
import com.mivanovskaya.humblr.domain.state.ProfileState
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRemoteRepositoryImpl
) : BaseViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.NotStartedYet)
    val state = _state.asStateFlow()

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = ProfileState.Loading
            _state.value = ProfileState.Success(repository.getProfile())
        }
    }
}