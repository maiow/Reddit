package com.mivanovskaya.humblr.presentation.onboarding

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mivanovskaya.humblr.databinding.ItemViewpagerBinding

class ViewPagerAdapter(
    private val allOnboardingHeaders: Array<String>,
    private val allOnboardingTexts: Array<String>,
    private val allOnboardingImages: Array<Drawable>,
   // private val buttonTexts: Array<String>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    class ViewPagerHolder(private val itemBinding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindImages(item: Drawable) = itemBinding.onboardingImages.setImageDrawable(item)

        fun bindHeaders(item: String) {
            itemBinding.onboardingHeaders.text = item
        }

        fun bindTexts(item: String) {
            itemBinding.onboardingTexts.text = item
        }
//        fun bindButton(item: String) {
//            itemBinding.authorizeButton.text = item
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(
            ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bindHeaders(allOnboardingHeaders[position])
        holder.bindTexts(allOnboardingTexts[position])
        holder.bindImages(allOnboardingImages[position])
        //holder.bindButton(buttonTexts[position])
    }

    override fun getItemCount(): Int = allOnboardingHeaders.size
}