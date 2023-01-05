package com.mivanovskaya.humblr.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashattestation.databinding.ItemViewpagerBinding

class ViewPagerAdapter(
    private val allOnboardingTexts: Array<String>,
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    class ViewPagerHolder(private val itemBinding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: String) {
            itemBinding.onboardingTexts.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(
            ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(allOnboardingTexts[position])
    }

    override fun getItemCount(): Int {
        return allOnboardingTexts.size
    }
}