package com.mivanovskaya.humblr.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
        setLogoutButton()
        //TODO: добавить кнопку Clear Saved после готовности экрана с функционалом сохранения
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
                binding.progressBar.isVisible = true
                binding.error.isVisible = false
            }
            is LoadState.Error -> {
                binding.containerView.isVisible = false
                binding.progressBar.isVisible = false
                binding.error.isVisible = true
            }
            is LoadState.Content -> {
                binding.containerView.isVisible = true
                binding.progressBar.isVisible = false
                binding.error.isVisible = false
                val data = state.data as Profile
                if (data.urlAvatar != null) loadAvatar(data.urlAvatar!!)
                loadProfileTexts(data)
            }
        }
    }

    private fun loadProfileTexts(data: Profile) {
        binding.userName.text = data.name
        binding.userId.text = getString(R.string.user_id, data.id)
        binding.karma.text = getString(R.string.karma, data.total_karma ?: 0)
        binding.followers.text =
            getString(R.string.followers, data.more_infos?.subscribers ?: "0")
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
