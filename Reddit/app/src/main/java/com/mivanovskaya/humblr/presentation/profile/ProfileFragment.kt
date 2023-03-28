package com.mivanovskaya.humblr.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.databinding.FragmentProfileBinding
import com.mivanovskaya.humblr.domain.models.Profile
import com.mivanovskaya.humblr.domain.state.LoadState
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
        setFriendsListClick()
        setClearSaved()
        setLogoutButton()
    }

    private fun getLoadingState() {
        viewModel.getProfile()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.state.collect { state -> updateUi(state) }
            }
    }

    private fun updateUi(state: LoadState) {
        when (state) {
            LoadState.NotStartedYet -> {}
            LoadState.Loading -> {
                binding.containerView.isVisible = false
                binding.common.progressBar.isVisible = true
                binding.common.error.isVisible = false
            }
            is LoadState.Error -> {
                binding.containerView.isVisible = false
                binding.common.progressBar.isVisible = false
                binding.common.error.isVisible = true
            }
            is LoadState.Content -> {
                binding.containerView.isVisible = true
                binding.common.progressBar.isVisible = false
                binding.common.error.isVisible = false
                val data = state.data as Profile
                if (data.urlAvatar != null) loadAvatar(data.urlAvatar!!)
                loadProfileTexts(data)
            }
        }
    }

    private fun loadProfileTexts(data: Profile) {
        with(binding) {
            userName.text = data.name
            userId.text = getString(R.string.user_id, data.more_infos?.title /*?: "N/A"*/)
            karma.text = getString(R.string.karma, data.total_karma ?: 0)
            followers.text =
                getString(R.string.followers, data.more_infos?.subscribers ?: "0")
        }
    }

    private fun loadAvatar(url: String) {
        binding.imageView.loadImage(
            viewModel.getClearedUrlAvatar(url)
        )
    }

    private fun setFriendsListClick() {
        binding.buttonListOfFriends.setOnClickListener {
            viewModel.navigateToFriends(this)
        }
    }

    private fun setClearSaved(){
        binding.buttonClearSaved.setOnClickListener {
            viewModel.clearSaved()
            Snackbar.make(binding.root, getString(R.string.snack_unsaved), BaseTransientBottomBar.LENGTH_SHORT).show()
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
                viewModel.logout(this)
            }
            .setNegativeButton(R.string.no) { _, _ ->
                dialog.create().hide()
            }
        dialog.create().show()
    }
}
