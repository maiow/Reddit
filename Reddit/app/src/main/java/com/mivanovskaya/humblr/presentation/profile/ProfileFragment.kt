package com.mivanovskaya.humblr.presentation.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_NAME
import com.mivanovskaya.humblr.data.state.LoadState
import com.mivanovskaya.humblr.databinding.FragmentProfileBinding
import com.mivanovskaya.humblr.domain.models.ProfileState
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
        setLogoutButton(createSharedPreference(TOKEN_SHARED_NAME))
    }

    private fun getLoadingState() {
        viewModel.getProfile()
        if (viewModel.loadState.value == LoadState.ERROR) {
            binding.error.isVisible = true
        }
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state.collect { state -> updateUi(state) }
            }
    }

    private fun updateUi(state: ProfileState) {
        when (state) {
            ProfileState.NotStartedYet -> {}
            ProfileState.Loading -> {
                binding.progressBar.isVisible = true
            }
            is ProfileState.Success -> {
                binding.progressBar.isVisible = false
                binding.userName.text = state.data.name
                binding.userId.text = "ID: " + state.data.id
                binding.imageView.loadImage(state.data.urlProfilePic!!)
                binding.karma.text = "Karma: " + state.data.total_karma
                setFriendsListClick(state.data.id)
            }
        }
    }

    private fun setFriendsListClick(userId: String) {
        binding.buttonListOfFriends.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections
                .actionNavigationProfileToNavigationFriends(userId))
        }
    }

    private fun setLogoutButton(preferences: SharedPreferences) {
        binding.buttonLogout.setOnClickListener {
            setAlertDialog(preferences)
            true
        }
    }

    private fun setAlertDialog(preferences: SharedPreferences) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(R.string.logout_title)
            .setMessage(R.string.logout_message)
            .setPositiveButton(R.string.yes) { _, _ ->
                preferences.edit().putString(TOKEN_SHARED_KEY, "").apply()
                preferences.edit().putBoolean(TOKEN_ENABLED_KEY, false).apply()
                findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToNavigationAuth())
            }
            .setNegativeButton(R.string.no) { _, _ ->
                dialog.create().hide()
            }
        dialog.create().show()
    }
}
