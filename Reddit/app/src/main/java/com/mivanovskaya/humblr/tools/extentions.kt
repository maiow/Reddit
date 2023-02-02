package com.mivanovskaya.humblr.tools

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.tabs.TabLayout
import com.mivanovskaya.humblr.R

fun ImageView.loadImage(urls: String) {
    Glide.with(this)
        .load(urls)
        .error(R.drawable.error_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun TabLayout.setSelectedTabListener(block: (position: Int) -> Unit){
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if (tab != null) { block(tab.position) }
        }
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}

//TODO: move here from home fragment:
//fun SearchView.setChangeTextListener(block: (query: String) -> Unit) {
//
//    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//
//        override fun onQueryTextChange(newText: String): Boolean {
//            block(newText)
//            return false
//        }
//
//        override fun onQueryTextSubmit(query: String): Boolean {
//            return false
//        }
//    })
//}
