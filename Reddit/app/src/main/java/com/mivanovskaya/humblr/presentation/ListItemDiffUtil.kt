package com.mivanovskaya.humblr.presentation

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.mivanovskaya.humblr.domain.ListItem

class ListItemDiffUtil : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
}