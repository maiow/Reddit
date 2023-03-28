package com.mivanovskaya.humblr.presentation.authorization

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.data.api.*
import com.mivanovskaya.humblr.domain.state.LoadState
import com.mivanovskaya.humblr.databinding.FragmentAuthBinding
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentAuthBinding.inflate(inflater)
    private val viewModel by viewModels<AuthViewModel>()
    private val args by navArgs<AuthFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAuthorizationButton()
        updateUiOnLoadStateChange()
        viewModel.createToken(args.code)
    }

    private fun setAuthorizationButton() {
        binding.authButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(CALL))
            startActivity(intent)
        }
    }

    private fun updateUiOnLoadStateChange() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    LoadState.NotStartedYet ->
                        setViewsStates(
                            buttonIsEnabled = true,
                            textIsVisible = false,
                            progressIsVisible = false
                        )
                    LoadState.Loading ->
                        setViewsStates(
                            buttonIsEnabled = false,
                            textIsVisible = false,
                            progressIsVisible = true
                        )
                    is LoadState.Content -> {
                        setViewsStates(
                            buttonIsEnabled = false,
                            textIsVisible = true,
                            progressIsVisible = false
                        )
                        findNavController().navigate(R.id.action_navigation_auth_to_navigation_home)
                    }
                    is LoadState.Error -> {
                        setViewsStates(
                            buttonIsEnabled = true,
                            textIsVisible = true,
                            progressIsVisible = false
                        )
                        binding.text.text = state.message
                        Log.e(TAG, "loading error: ${state.message}")
                    }
                }
            }
        }
    }

    private fun setViewsStates(
        buttonIsEnabled: Boolean,
        textIsVisible: Boolean,
        progressIsVisible: Boolean
    ) {
        binding.authButton.isEnabled = buttonIsEnabled
        binding.text.isVisible = textIsVisible
        binding.progressBar.isVisible = progressIsVisible
    }
}
