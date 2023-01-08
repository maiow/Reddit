package com.mivanovskaya.humblr.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.mivanovskaya.humblr.databinding.FragmentProfileBinding
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentProfileBinding.inflate(inflater)
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textNotifications.text = it
        }
    }
}