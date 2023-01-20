package com.mivanovskaya.humblr.presentation.home

import androidx.lifecycle.viewModelScope

import com.mivanovskaya.humblr.domain.ListTypes
import com.mivanovskaya.humblr.domain.Query
import com.mivanovskaya.humblr.domain.repository.SubredditsRemoteRepository
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: SubredditsRemoteRepository) :
    BaseViewModel() {

    private val _query = Query()
    val query get() = _query.source

    fun setSource(position: Int) {
        _query.source = if (position == 0) NEW else POPULAR
        getSubreddits()
    }

    fun getSubreddits() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = LoadState.Loading
            _state.value =
                LoadState.Content(repository.getList(ListTypes.SUBREDDIT, query,/* "1"*/))
        }
    }

    companion object {
        private const val NEW = "new"
        private const val POPULAR = ""
    }
}