package com.mivanovskaya.humblr.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mivanovskaya.humblr.data.state.LoadState
import com.mivanovskaya.humblr.databinding.FragmentFriendsBinding
import com.mivanovskaya.humblr.domain.state.FriendsState
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment : BaseFragment<FragmentFriendsBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFriendsBinding.inflate(inflater)
    private val viewModel by viewModels<FriendsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFriends()
        if (viewModel.loadState.value == LoadState.ERROR) {
            binding.error.isVisible = true
        }
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state.collect { state -> updateUi(state) }
            }
    }

    private fun updateUi(state: FriendsState) {
        when (state) {
            FriendsState.NotStartedYet -> {}
            FriendsState.Loading -> {
                binding.progressBar.isVisible = true
            }
            is FriendsState.Success -> {
                binding.progressBar.isVisible = false
                binding.friendsTest.text = state.data.data.friends_list.toString()
            }
        }
    }
}