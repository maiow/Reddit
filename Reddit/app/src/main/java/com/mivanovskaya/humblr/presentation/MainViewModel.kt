package com.mivanovskaya.humblr.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.data.api.ONBOARDING_IS_SHOWN
import com.mivanovskaya.humblr.data.api.TOKEN_ENABLED_KEY
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_NAME
import com.mivanovskaya.humblr.domain.sharedpreferences.SharedPrefsService

class MainViewModel : ViewModel() {

    fun setNavigation(context: Context, fragment: Fragment) {
        val prefs = SharedPrefsService.create(context, TOKEN_SHARED_NAME)

        /**для тестирования без онбординга раскомментировать две строки ниже
         * и закомментировать все остальные ниже этих двух*/
        // fragment.findNavController().navigate(MainFragmentDirections
        //     .actionMainFragmentToAuthFragment())

        val toOnboardingFragment = MainFragmentDirections.actionMainFragmentToNavigationOnboarding()
        val toAuthFragment = MainFragmentDirections.actionMainFragmentToAuthFragment()
        val toHomeFragment = MainFragmentDirections.actionMainFragmentToNavigationHome()

        if (prefs.getBoolean(TOKEN_ENABLED_KEY, false))
            fragment.findNavController().navigate(toHomeFragment)
        else {
            if (prefs.getBoolean(ONBOARDING_IS_SHOWN, false))
                fragment.findNavController().navigate(toAuthFragment)
            else fragment.findNavController().navigate(toOnboardingFragment)
        }
    }
}