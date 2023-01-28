package com.mivanovskaya.humblr.presentation.delegates

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.mivanovskaya.humblr.databinding.ItemFriendsBinding
import com.mivanovskaya.humblr.domain.models.Friend
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.tools.loadImage

fun friendsDelegate() = adapterDelegateViewBinding<Friend, ListItem, ItemFriendsBinding>(
    { inflater, root -> ItemFriendsBinding.inflate(inflater, root, false) }
) {
    bind {
        binding.name = item.name
        binding.id = item.id
        binding.friendPhoto.loadImage(item.avatar_url)
        binding.executePendingBindings()
    }
}
