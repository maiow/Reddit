package com.mivanovskaya.humblr.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.data.api.ONBOARDING_IS_SHOWN
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_NAME
import com.mivanovskaya.humblr.databinding.FragmentMainBinding
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentMainBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = createSharedPreference(TOKEN_SHARED_NAME)
//        val toOnboardingFragment = MainFragmentDirections.actionMainFragmentToNavigationOnboarding()
        val toAuthFragment = MainFragmentDirections.actionMainFragmentToAuthFragment()
/*        val toHomeFragment = MainFragmentDirections.actionMainFragmentToNavigationHome()

        if (prefs.getBoolean(TOKEN_ENABLED_KEY, false))
            findNavController().navigate(toHomeFragment)
        else {
            if (prefs.getBoolean(ONBOARDING_IS_SHOWN, false))*/
                findNavController().navigate(toAuthFragment)
         //   else findNavController().navigate(toOnboardingFragment)
        }
    }
