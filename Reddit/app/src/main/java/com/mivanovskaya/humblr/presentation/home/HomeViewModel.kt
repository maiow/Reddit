package com.mivanovskaya.humblr.presentation.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mivanovskaya.humblr.data.repository.PagingSource
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.models.Subreddit
import com.mivanovskaya.humblr.domain.repository.SubredditsRemoteRepository
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.domain.tools.ListTypes
import com.mivanovskaya.humblr.domain.tools.Query
import com.mivanovskaya.humblr.domain.tools.SubQuery
import com.mivanovskaya.humblr.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SubredditsRemoteRepository
) : BaseViewModel() {

    private val _query = Query()
    private val subredditsSource get() = _query.source

    private val _subredditsSourceFlow = MutableStateFlow(subredditsSource)

    fun setSource(position: Int) {
        _query.source = if (position == FIRST_POSITION_INDEX) NEW else POPULAR
        _subredditsSourceFlow.value = subredditsSource
        getSubreddits()
    }

    fun getSubreddits() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = LoadState.Loading
            //loading is so fast, that progress bar isn't seen without delay
            //delay(1_000)
            getSubredditsList(subredditsSource, ListTypes.SUBREDDIT)
            _state.value = LoadState.Content()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    var subredditsList: Flow<PagingData<ListItem>> =
        _subredditsSourceFlow.asStateFlow().flatMapLatest { source ->
            if ((source == POPULAR) || (source == NEW)) {
                getSubredditsList(source, ListTypes.SUBREDDIT).flow
            } else {
                getSubredditsList(source, ListTypes.SUBREDDITS_SEARCH).flow
            }
        }.cachedIn(CoroutineScope(Dispatchers.IO))


    private fun getSubredditsList(
        source: String?,
        listType: ListTypes
    ): Pager<String, ListItem> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE_SUBREDDITS),
            pagingSourceFactory = { PagingSource(repository, source, listType) }
        )

    fun subscribe(subQuery: SubQuery) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.subscribeOnSubreddit(subQuery.action, subQuery.name)
        }
    }

    fun navigateToSingleSubreddit(fragment: Fragment, item: ListItem) {
        fragment.findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToNavigationSingleSubredditFragment(
                (item as Subreddit).namePrefixed
            )
        )
    }

    fun onSearchButtonClick(text: String) {
        viewModelScope.launch(Dispatchers.Main + handler) {
            _state.value = LoadState.Loading
            _query.source = text
            _subredditsSourceFlow.value = subredditsSource
            getSubredditsList(text, ListTypes.SUBREDDITS_SEARCH)
            _state.value = LoadState.Content()
        }
    }

    companion object {
        private const val FIRST_POSITION_INDEX = 0
        private const val NEW = "new"
        private const val POPULAR = ""
        private const val PAGE_SIZE_SUBREDDITS = 10
    }
}