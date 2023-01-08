package com.mivanovskaya.humblr.presentation.profile

import androidx.lifecycle.viewModelScope
import com.mivanovskaya.humblr.data.repository.ProfileRemoteRepositoryImpl
import com.mivanovskaya.humblr.data.state.LoadState
import com.mivanovskaya.humblr.domain.models.ProfileState
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val repository: ProfileRemoteRepositoryImpl
) : BaseViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.NotStartedYet)
    val state = _state.asStateFlow()

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loadState.value = LoadState.SUCCESS
            _state.value = ProfileState.Success(repository.getProfile())
        }
    }
}