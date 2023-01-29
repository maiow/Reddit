package com.mivanovskaya.humblr.presentation.favourites

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
import com.mivanovskaya.humblr.domain.tools.*
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
class FavouritesViewModel @Inject constructor(
    private val repository: SubredditsRemoteRepository
) : BaseViewModel() {

    private val query = FavoritesQuery()
    private val _thingFlow = MutableStateFlow(Change(query))

    fun setQuery(position: Int, isTabSource: Boolean) =
        if (isTabSource) setSource(position) else setType(position)

    private fun setSource(position: Int) {
        if (position == FIRST_POSITION_INDEX) {
            query.source = ALL
            if (query.listing == ListTypes.SAVED_POST) query.listing = ListTypes.POST
            if (query.listing == ListTypes.SUBSCRIBED_SUBREDDIT) query.listing = ListTypes.SUBREDDIT
        } else {
            query.source = SAVED
            if (query.listing == ListTypes.POST) query.listing = ListTypes.SAVED_POST
            if (query.listing == ListTypes.SUBREDDIT) query.listing = ListTypes.SUBSCRIBED_SUBREDDIT
        }
        _thingFlow.value = Change(query)
    }

    private fun setType(position: Int) {
        if (query.source == ALL) {
            query.listing =
                if (position == FIRST_POSITION_INDEX) ListTypes.POST else ListTypes.SUBREDDIT

        } else {
            query.listing =
                if (position == FIRST_POSITION_INDEX) ListTypes.SAVED_POST else ListTypes.SUBSCRIBED_SUBREDDIT
        }
        _thingFlow.value = Change(query)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    var thingList: Flow<PagingData<ListItem>> =
        _thingFlow.asStateFlow().flatMapLatest { query ->
            getThingList(query.value.listing, query.value.source).flow
        }.cachedIn(CoroutineScope(Dispatchers.IO))

    private fun getThingList(listType: ListTypes, source: String?): Pager<String, ListItem> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE_SUBREDDITS),
            pagingSourceFactory = { PagingSource(repository, source, listType) }
        )

    fun subscribe(subQuery: SubQuery) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.subscribeOnSubreddit(subQuery.action, subQuery.name)
        }
    }

    fun votePost(voteDirection: Int, postName: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.votePost(voteDirection, postName)
        }
    }

    fun savePost(postName: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.savePost(postName)
        }
    }

    fun unsavePost(postName: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            repository.unsavePost(postName)
        }
    }

    fun navigateToSingleSubreddit(fragment: Fragment, item: ListItem) {
        fragment.findNavController().navigate(
            FavouritesFragmentDirections
                .actionNavigationFavouritesToNavigationSingleSubredditFragment(
                    (item as Subreddit).namePrefixed)
        )
    }

    fun navigateToUser(fragment: Fragment, name: String) {
        fragment.findNavController().navigate(
            FavouritesFragmentDirections
                .actionNavigationFavouritesToNavigationUser(name)
        )
    }

    companion object {
        private const val FIRST_POSITION_INDEX = 0
        private const val ALL = ""
        private const val SAVED = "saved"
        private const val PAGE_SIZE_SUBREDDITS = 10
    }
}