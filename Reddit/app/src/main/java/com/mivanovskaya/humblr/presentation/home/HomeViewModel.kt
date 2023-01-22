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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SubredditsRemoteRepository
) :
    BaseViewModel() {

    /**работает без показа прогресс бара, и тут пока по-черному написано, надо подчистить*/

    private val _query = Query()
    val query get() = _query.source

    private val _thingFlow = MutableStateFlow(query)

//    private val _sub = SubQuery()
//    val sub get() = _sub.action
//    val sub_name get() = _sub.name
//    private val _subFlow = MutableStateFlow(sub)
//    private val _subFlow = MutableStateFlow(sub_name)

    fun setSource(position: Int) {
        _query.source = if (position == 0) NEW else POPULAR
        _thingFlow.value = query
        getSubreddits()
    }

    fun getSubreddits() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.value = LoadState.Loading
            getSubredditsList(ListTypes.SUBREDDIT, query)
            _state.value = LoadState.Content("")
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    var thingList: Flow<PagingData<ListItem>> =
        _thingFlow.asStateFlow().flatMapLatest { query ->
            getSubredditsList(ListTypes.SUBREDDIT, query).flow
        }.cachedIn(CoroutineScope(Dispatchers.IO))

    private fun getSubredditsList(listType: ListTypes, source: String?): Pager<String, ListItem> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { PagingSource(repository, source, listType) }
        )

    fun subscribe(subQuery: SubQuery) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.subscribeOnSubreddit(subQuery.action, subQuery.name)
        }
    }

    fun navigate(fragment: Fragment, item: ListItem) {
        fragment.findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToNavigationSingleSubredditFragment(
                (item as Subreddit).namePrefixed
            )
        )
    }

    companion object {
        private const val NEW = "new"
        private const val POPULAR = ""
    }
}