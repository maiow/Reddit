package com.mivanovskaya.humblr.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.mivanovskaya.humblr.databinding.FragmentHomeBinding
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textHome.text = it
        }
    }
}