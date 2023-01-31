package com.mivanovskaya.humblr.presentation.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.databinding.FragmentFavouritesBinding
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.tools.ClickableView
import com.mivanovskaya.humblr.domain.tools.SUBSCRIBE
import com.mivanovskaya.humblr.domain.tools.SubQuery
import com.mivanovskaya.humblr.presentation.home.HomePagedDataDelegationAdapter
import com.mivanovskaya.humblr.tools.BaseFragment
import com.mivanovskaya.humblr.tools.setSelectedTabListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFavouritesBinding.inflate(inflater)
    private val viewModel by viewModels<FavouritesViewModel>()

    private val adapter by lazy { HomePagedDataDelegationAdapter {
            subQuery: SubQuery, item: ListItem, clickableView: ClickableView ->
            onClick(subQuery, item, clickableView) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadContent()
        tabLayoutSelectedListener(binding.toggleType, false)
        tabLayoutSelectedListener(binding.toggleSource, true)
        loadStateItemsObserve()
    }

    private fun loadContent() {
        binding.recyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.thingList.collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun loadStateItemsObserve() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            adapter.loadStateFlow.collect { state ->
                binding.common.progressBar.isVisible =
                    state.refresh is LoadState.Loading || state.append is LoadState.Loading
                binding.common.error.isVisible =
                    state.refresh is LoadState.Error || state.append is LoadState.Error || state.prepend is LoadState.Error
                binding.noSavedPosts.isVisible =
                    state.refresh is LoadState.NotLoading && adapter.itemCount == 0
            }
        }
    }

    private fun tabLayoutSelectedListener(tabLayout: TabLayout, isSource: Boolean) {
        tabLayout.setSelectedTabListener { position ->
            viewModel.setQuery(position, isSource)
        }
    }

    private fun onClick(subQuery: SubQuery, item: ListItem, clickableView: ClickableView) {
        when (clickableView) {
            ClickableView.SAVE -> viewModel.savePost(postName = subQuery.name)
            ClickableView.UNSAVE -> viewModel.unsavePost(postName = subQuery.name)
            ClickableView.VOTE ->
                viewModel.votePost(voteDirection = subQuery.voteDirection, postName = subQuery.name)
            ClickableView.SUBSCRIBE -> {
                viewModel.subscribe(subQuery)
                val text =
                    if (subQuery.action == SUBSCRIBE) getString(R.string.subscribed)
                    else getString(R.string.unsubscribed)
                Snackbar.make(binding.recyclerView, text, BaseTransientBottomBar.LENGTH_SHORT)
                    .show()
            }
            ClickableView.USER -> viewModel.navigateToUser(this, subQuery.name)
            ClickableView.SUBREDDIT ->
                viewModel.navigateToSingleSubreddit(this, item)
        }
    }
}


