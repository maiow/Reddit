package com.mivanovskaya.humblr.presentation.onboarding

import android.content.Context
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.mivanovskaya.humblr.R

class ChangeButtonTextOnPageChange(
    private val skipButton: TextView,
    private val context: Context,
    private val lastPageIndex: Int
) :
    ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        if (position == lastPageIndex) skipButton.text = context.getString(R.string.ready)
        else skipButton.text = context.getString(R.string.skip)
    }
}