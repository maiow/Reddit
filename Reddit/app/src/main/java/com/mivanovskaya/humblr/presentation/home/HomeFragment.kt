package com.mivanovskaya.humblr.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.paging.PagedListDelegationAdapter
import com.mivanovskaya.humblr.databinding.FragmentHomeBinding
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.models.Subreddits
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.presentation.ListItemDiffUtil
import com.mivanovskaya.humblr.tools.BaseFragment
import com.mivanovskaya.humblr.tools.PagedDataDelegationAdapter
import com.mivanovskaya.humblr.tools.setSelectedTabListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)
    private val viewModel by viewModels<HomeViewModel>()

    //    private val adapter by lazy {
//        ThingPagingAdapter { clickableView, item -> onClick(clickableView, item) }
//    }
    //private val adapter by lazy { PagedListDelegationAdapter(ListItemDiffUtil(), HomeScreenDelegates.subredditsDelegate) }
    private val adapter by lazy { ListDelegationAdapter(HomeScreenDelegates.subredditsDelegate) }
    //private val adapter by lazy { OneCategoryForPaging()}

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
                loadContent(state.data as Subreddits)
            }
            is LoadState.Error -> {
                binding.progressBar.isVisible = false
                binding.error.isVisible = true
                binding.recyclerView.isVisible = false
            }
        }
    }

//    private fun observePagingData() {
//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            viewModel.thingList.collect { pagingData ->
//                adapter.submitData(pagingData)
//            }
//        }
//    }

    private fun loadContent(data: Subreddits) {
        adapter.items = data.subreddits_list
        //TODO: продолжить с пагинацией - добавить пейджинг сорс
       // adapter.submitList(data.subreddits_list as PagedList<ListItem>)
        binding.recyclerView.adapter = adapter
    }


    private fun tabLayoutListener(tabLayout: TabLayout) {
        tabLayout.setSelectedTabListener { position ->
            viewModel.setSource(position)
        }
    }

    private fun setSearch() {
        binding.searchView.setOnSearchClickListener {
            Snackbar.make(binding.recyclerView, "Humblr doesn't provide search functionality yet",
                LENGTH_SHORT).show()
            }
    }
}

