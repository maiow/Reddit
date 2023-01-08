package com.mivanovskaya.humblr.presentation.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.mivanovskaya.humblr.databinding.FragmentFavouritesBinding
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFavouritesBinding.inflate(inflater)
    private val viewModel by viewModels<FavouritesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textFavourites.text = it
        }
    }
}