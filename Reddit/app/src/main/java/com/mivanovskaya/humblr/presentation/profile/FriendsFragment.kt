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
import com.mivanovskaya.humblr.domain.models.FriendsWrapper
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment : BaseFragment<FragmentFriendsBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFriendsBinding.inflate(inflater)
    private val viewModel by viewModels<FriendsViewModel>()

    //задача данного adapter - зарегистрировать view types
    //private val adapter = ListDelegationAdapter(FriendsScreenDelegates.friendsDelegate)
    //почитать:
    private val adapter by lazy { ListDelegationAdapter(FriendsScreenDelegates.friendsDelegate)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getLoadingState()
        setToolbarBackButton()
        with(binding) {
            adapter.items = listOf(
                FriendRVItem("1", "name1"), FriendRVItem("2", "name2"),
                FriendRVItem("3", "name3"), FriendRVItem("4", "name4"),
                FriendRVItem("0", "name0"), FriendRVItem("5", "name5"),
                FriendRVItem("6", "name6"), FriendRVItem("7", "name7")
            )
            recyclerView.adapter = adapter
        }
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
            }
            is LoadState.Content -> {
                binding.progressBar.isVisible = false
                binding.error.isVisible = false
                val data = state.data as FriendsWrapper

                //binding.friendsTest.text = data.data.friends_list.toString()
            }
            is LoadState.Error -> {
                binding.progressBar.isVisible = false
                binding.error.isVisible = true
            }
        }
    }

    private fun setToolbarBackButton() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(
                FriendsFragmentDirections.actionNavigationFriendsToNavigationProfile()
            )
        }
    }
}