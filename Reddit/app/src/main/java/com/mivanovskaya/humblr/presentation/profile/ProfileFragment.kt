package com.mivanovskaya.humblr.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.databinding.FragmentProfileBinding
import com.mivanovskaya.humblr.domain.state.ProfileState
import com.mivanovskaya.humblr.tools.BaseFragment
import com.mivanovskaya.humblr.tools.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentProfileBinding.inflate(inflater)
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getLoadingState()
        setLogoutButton()
    }

    private fun getLoadingState() {
        viewModel.getProfile()
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state.collect { state -> updateUi(state) }
            }
    }

    private fun updateUi(state: ProfileState) {
        when (state) {
            ProfileState.NotStartedYet -> {}
            ProfileState.Loading -> {
                showBindItems(false)
            }
            is ProfileState.Content -> {
                showBindItems(true)
                loadAvatar(state.data.urlAvatar!!)// ?: ...

                binding.userName.text = state.data.name
                binding.userId.text = getString(R.string.user_id, state.data.id)
                binding.subscribers.text =
                    getString(R.string.followers, state.data.more_infos?.subscribers ?: "0")
                binding.karma.text = getString(R.string.karma, state.data.total_karma ?: 0)

                setFriendsListClick(state.data.id)
            }
            is ProfileState.Error -> {
                binding.progressBar.isVisible = false
                binding.error.isVisible = true
                showBindItems(false)
            }
        }
    }

    private fun showBindItems(show: Boolean) {
        binding.userDataFrame.isVisible = show
        binding.buttonListOfFriends.isVisible = show
        binding.buttonClearSaved.isVisible = show
        binding.buttonLogout.isVisible = show
        binding.progressBar.isVisible = !show
    }

    private fun loadAvatar(url: String) {
        binding.imageView.loadImage(
            viewModel.getClearedUrlAvatar(url)
        )
    }

    private fun setFriendsListClick(userId: String) {
        binding.buttonListOfFriends.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections
                    .actionNavigationProfileToNavigationFriends(userId)
            )
        }
    }

    private fun setLogoutButton() {
        binding.buttonLogout.setOnClickListener {
            setAlertDialog()
        }
    }

    private fun setAlertDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(R.string.logout_title)
            .setMessage(R.string.logout_message)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.logout(requireContext(), this)
            }
            .setNegativeButton(R.string.no) { _, _ ->
                dialog.create().hide()
            }
        dialog.create().show()
    }
}
