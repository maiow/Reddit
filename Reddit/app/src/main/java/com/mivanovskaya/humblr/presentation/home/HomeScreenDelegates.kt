package com.mivanovskaya.humblr.presentation.home

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.mivanovskaya.humblr.databinding.ItemSubredditBinding
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.models.Subreddit
import com.mivanovskaya.humblr.domain.tools.SubQuery

fun subredditsDelegate(
    onClick: (subQuery: SubQuery) -> Unit,
) = adapterDelegateViewBinding<Subreddit, ListItem, ItemSubredditBinding>(
    { inflater, root -> ItemSubredditBinding.inflate(inflater, root, false) }
) {
    bind {
        binding.subredditTitle.text = item.namePrefixed
        binding.subredditDescription.text = item.description
        binding.subscribeButton.isSelected = item.isUserSubscriber == true
    }
    binding.subscribeButton.setOnClickListener {
        binding.subscribeButton.isSelected = !binding.subscribeButton.isSelected
        val action = if (!binding.subscribeButton.isSelected) "unsub" else "sub"
        onClick(SubQuery(name = item.name, action = action))
    }
}