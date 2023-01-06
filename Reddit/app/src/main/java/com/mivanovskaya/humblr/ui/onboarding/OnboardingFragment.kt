package com.mivanovskaya.humblr.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.data.api.ONBOARDING_IS_SHOWN
import com.mivanovskaya.humblr.data.api.TOKEN_SHARED_NAME
import com.mivanovskaya.humblr.databinding.FragmentOnboardingBinding
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentOnboardingBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        setTabs()
        setAuthorizeButton()
        saveOnboardingShown()
    }

    private fun setViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter(
            resources.getStringArray(R.array.onboarding_texts_array1),
            resources.getStringArray(R.array.onboarding_texts_array2),
            arrayOf(
                ResourcesCompat.getDrawable(resources, R.drawable.onb_image1, null)!!,
                ResourcesCompat.getDrawable(resources, R.drawable.onb_image2, null)!!,
                ResourcesCompat.getDrawable(resources, R.drawable.onb_image3, null)!!
            ),
            // resources.getStringArray(R.array.button_array)
        )
        //binding.viewPager.registerOnPageChangeCallback(AnimateImageOnPageChange(binding.ellipseImage))
    }

    private fun setTabs() = TabLayoutMediator(binding.tabs, binding.viewPager) { _, _ -> }.attach()

    private fun setAuthorizeButton() {
        binding.authorizeButton.background = null
        binding.authorizeButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_onboarding_to_navigation_auth)
        }
    }

    private fun saveOnboardingShown() {
        val prefs = createSharedPreference(TOKEN_SHARED_NAME)
        prefs.edit().putBoolean(ONBOARDING_IS_SHOWN, true).apply()
    }
}