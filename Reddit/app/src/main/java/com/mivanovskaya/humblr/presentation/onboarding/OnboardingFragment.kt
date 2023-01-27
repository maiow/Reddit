package com.mivanovskaya.humblr.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.data.api.ONBOARDING_IS_SHOWN
import com.mivanovskaya.humblr.databinding.FragmentOnboardingBinding
import com.mivanovskaya.humblr.domain.sharedpreferences.SharedPrefsService
import com.mivanovskaya.humblr.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingFragment @Inject constructor(
    private val sharedPrefsService: SharedPrefsService
): BaseFragment<FragmentOnboardingBinding>() {

    private var mediator: TabLayoutMediator? = null

    override fun initBinding(inflater: LayoutInflater) = FragmentOnboardingBinding.inflate(inflater)

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
            /**можно было бы с id и грузить glide'ом, но он тормозит, и заранее кэшировать
             * векторы не может, а так очень быстро всё подгружается*/
            arrayOf(
                ResourcesCompat.getDrawable(resources, R.drawable.onb_image1, null)
                    ?: error("lost onboarding image1"),
                ResourcesCompat.getDrawable(resources, R.drawable.onb_image2, null)
                    ?: error("lost onboarding image2"),
                ResourcesCompat.getDrawable(resources, R.drawable.onb_image3, null)
                    ?: error("lost onboarding image3"),
            )
        )
        binding.viewPager.registerOnPageChangeCallback(
            ChangeButtonTextOnPageChange(
                binding.skipButton,
                requireContext(),
                resources.getStringArray(R.array.onboarding_texts_array1).lastIndex
            )
        )
    }

    private fun setTabs() {
        mediator = TabLayoutMediator(binding.tabs, binding.viewPager) { _, _ -> }
        mediator!!.attach()
    }

    private fun setAuthorizeButton() {
        binding.skipButton.background = null
        binding.skipButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_onboarding_to_navigation_auth)
        }
    }

    private fun saveOnboardingShown() {
        sharedPrefsService.save(requireContext(), ONBOARDING_IS_SHOWN, true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediator?.detach()
        mediator = null
    }
}