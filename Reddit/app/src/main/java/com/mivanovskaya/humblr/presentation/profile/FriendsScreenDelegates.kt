package com.mivanovskaya.humblr.presentation.profile

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.mivanovskaya.humblr.databinding.ItemFriendsBinding
import com.mivanovskaya.humblr.tools.ListItem

object FriendsScreenDelegates {

    val friendsDelegate = adapterDelegateViewBinding<FriendRVItem, ListItem, ItemFriendsBinding>(
        { inflater, root -> ItemFriendsBinding.inflate(inflater, root, false) }
    ) {
        bind {
            binding.name = item.name
            binding.id = item.id
            binding.executePendingBindings()
        }
    }
}