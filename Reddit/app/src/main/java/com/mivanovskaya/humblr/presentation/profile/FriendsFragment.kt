package com.mivanovskaya.humblr.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.mivanovskaya.humblr.databinding.FragmentFriendsBinding
import com.mivanovskaya.humblr.domain.models.Friends
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment : BaseFragment<FragmentFriendsBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFriendsBinding.inflate(inflater)
    private val viewModel by viewModels<FriendsViewModel>()

    //задача данного adapter - зарегистрировать view types
    private val adapter by lazy { ListDelegationAdapter(FriendsScreenDelegates.friendsDelegate) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getLoadingState()
        setToolbarBackButton()
    }

    private fun getLoadingState() {
        viewModel.getFriends()
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
                loadContent(state.data as Friends)
            }
            is LoadState.Error -> {
                binding.progressBar.isVisible = false
                binding.error.isVisible = true
                binding.recyclerView.isVisible = false
            }
        }
    }

    private fun loadContent(data: Friends) {
        adapter.items = data.friends_list
        binding.recyclerView.adapter = adapter
    }

    private fun setToolbarBackButton() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(
                FriendsFragmentDirections.actionNavigationFriendsToNavigationProfile()
            )
        }
    }
}