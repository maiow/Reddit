package com.mivanovskaya.humblr.presentation.onboarding

import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2

class AnimateImageOnPageChange(val image: ImageView): ViewPager2.OnPageChangeCallback() {
    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        image.translationY = (positionOffset + position) * 100
    }
}