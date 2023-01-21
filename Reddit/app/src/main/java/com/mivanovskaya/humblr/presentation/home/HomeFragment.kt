package com.mivanovskaya.humblr.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.mivanovskaya.humblr.databinding.FragmentHomeBinding
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.domain.tools.SubQuery
import com.mivanovskaya.humblr.tools.BaseFragment
import com.mivanovskaya.humblr.tools.setSelectedTabListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)
    private val viewModel by viewModels<HomeViewModel>()

    // private val adapter by lazy { PagedListDelegationAdapter(ListItemDiffUtil(), HomeScreenDelegates.subredditsDelegate) }
    //private val adapter by lazy { ListDelegationAdapter(HomeScreenDelegates.subredditsDelegate) }
    private val adapter by lazy { HomePagedDataDelegationAdapter{onClick(it)} }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getLoadingState()
        setAdapter()
        tabLayoutListener(binding.toggleSource)
        setSearch()
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
    }

    private fun getLoadingState() {
        viewModel.getSubreddits()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state -> updateUi(state) }
        }
    }

    private fun updateUi(state: LoadState) {
        when (state) {
            LoadState.NotStartedYet -> {}
            LoadState.Loading -> {
                binding.progressBar.isVisible = true
                binding.error.isVisible = false
                binding.recyclerView.isVisible = false
            }
            is LoadState.Content -> {
                binding.progressBar.isVisible = false
                binding.error.isVisible = false
                binding.recyclerView.isVisible = true
                loadContent()
            }
            is LoadState.Error -> {
                binding.progressBar.isVisible = false
                binding.error.isVisible = true
                binding.recyclerView.isVisible = false
            }
        }
    }


    private fun loadContent() {
        binding.recyclerView.adapter = adapter

        //private fun observePagingData() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.thingList.collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun tabLayoutListener(tabLayout: TabLayout) {
        tabLayout.setSelectedTabListener { position ->
            viewModel.setSource(position)
        }
    }

    private fun setSearch() {
        binding.searchView.setOnSearchClickListener {
            Snackbar.make(
                binding.recyclerView, "Humblr doesn't provide search functionality yet",
                LENGTH_SHORT
            ).show()
        }
    }

    private fun onClick(subQuery: SubQuery/*item: ListItem, clickableView: ClickableView, item: ListItem*/) {
//        if (clickableView == ClickableView.SUBREDDIT) {
//            findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToSingleSubredditFragment(
//                    (item as Subreddit).namePrefixed
//                )
//            )
//        }
//        if (clickableView == ClickableView.SUBSCRIBE) Toast.makeText(
//            requireContext(),
//            "subscribed",
//            Toast.LENGTH_SHORT
//        ).show()
        viewModel.subscribe(subQuery)
    }
}


