package com.mivanovskaya.humblr.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mivanovskaya.humblr.databinding.FragmentProfileBinding
import com.mivanovskaya.humblr.domain.models.ProfileState
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentProfileBinding.inflate(inflater)
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getLoadingState()

    }

    private fun getLoadingState() {
        viewModel.getProfile()
        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state.collect { loadState -> updateUiOnServerResponse(loadState) }
            }
    }

    private fun updateUiOnServerResponse(loadState: ProfileState) {
        when (loadState) {
            ProfileState.NotStartedYet -> {}
            is ProfileState.Success -> {
                binding.textNotifications.text = loadState.data.name
            }
        }
    }
}
