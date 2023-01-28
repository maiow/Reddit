package com.mivanovskaya.humblr.presentation.onboarding

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.data.api.ONBOARDING_IS_SHOWN
import com.mivanovskaya.humblr.domain.sharedpreferences.SharedPrefsService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val sharedPrefsService: SharedPrefsService) :
    ViewModel() {

    fun saveOnboardingShown(context: Context) {
        sharedPrefsService.save(context, ONBOARDING_IS_SHOWN, true)
    }

    fun navigateToAuth(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_navigation_onboarding_to_navigation_auth)
    }
}