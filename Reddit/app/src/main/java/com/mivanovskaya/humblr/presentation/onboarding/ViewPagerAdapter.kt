package com.mivanovskaya.humblr.presentation.onboarding

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mivanovskaya.humblr.databinding.ItemViewpagerBinding

class ViewPagerAdapter(
    private val allOnboardingHeaders: Array<String>,
    private val allOnboardingTexts: Array<String>,
    private val allOnboardingImages: Array<Drawable>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    class ViewPagerHolder(private val itemBinding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemHeaders: String, itemTexts: String, itemImages: Drawable) {
            itemBinding.onboardingHeaders.text = itemHeaders
            itemBinding.onboardingTexts.text = itemTexts
            itemBinding.onboardingImages.setImageDrawable(itemImages)
    }
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(
            ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(
            allOnboardingHeaders[position],
            allOnboardingTexts[position],
            allOnboardingImages[position]
        )
    }

    override fun getItemCount(): Int = allOnboardingHeaders.size
}