package com.mivanovskaya.humblr.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.data.api.ONBOARDING_IS_SHOWN
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.domain.sharedpreferences.SharedPrefsService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sharedPrefsService: SharedPrefsService) :
    ViewModel() {

    fun setNavigation(context: Context, fragment: Fragment) {

        /**для тестирования без онбординга раскомментировать две строки ниже
         * и закомментировать все остальные ниже этих двух*/
        // fragment.findNavController().navigateToFriends(MainFragmentDirections
        //     .actionMainFragmentToAuthFragment())

        val toOnboardingFragment = MainFragmentDirections.actionMainFragmentToNavigationOnboarding()
        val toAuthFragment = MainFragmentDirections.actionMainFragmentToAuthFragment()
        val toHomeFragment = MainFragmentDirections.actionMainFragmentToNavigationHome()

        if (sharedPrefsService.load(context, TOKEN_ENABLED_KEY))
            fragment.findNavController().navigate(toHomeFragment)
        else {
            if (sharedPrefsService.load(context, ONBOARDING_IS_SHOWN))
                fragment.findNavController().navigate(toAuthFragment)
            else fragment.findNavController().navigate(toOnboardingFragment)
        }
    }
}