package com.mivanovskaya.humblr.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.data.api.ONBOARDING_IS_SHOWN
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.domain.storageservice.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val storageService: StorageService) :
    ViewModel() {

    fun setNavigation(fragment: Fragment) {

        val toOnboardingFragment = MainFragmentDirections.actionMainFragmentToNavigationOnboarding()
        val toAuthFragment = MainFragmentDirections.actionMainFragmentToAuthFragment()
        val toHomeFragment = MainFragmentDirections.actionMainFragmentToNavigationHome()

        if (storageService.load(TOKEN_ENABLED_KEY))
            fragment.findNavController().navigate(toHomeFragment)
        else {
            if (storageService.load(ONBOARDING_IS_SHOWN))
                fragment.findNavController().navigate(toAuthFragment)
            else fragment.findNavController().navigate(toOnboardingFragment)
        }
    }
}