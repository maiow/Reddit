package com.mivanovskaya.humblr.presentation.delegates

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.mivanovskaya.humblr.databinding.ItemSubredditBinding
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.models.SearchSubreddit
import com.mivanovskaya.humblr.domain.tools.ClickableView
import com.mivanovskaya.humblr.domain.tools.SUBSCRIBE
import com.mivanovskaya.humblr.domain.tools.SubQuery
import com.mivanovskaya.humblr.domain.tools.UNSUBSCRIBE

fun subredditsSearchDelegate(
    onClick: (subQuery: SubQuery, item: ListItem, clickableView: ClickableView) -> Unit,
) = adapterDelegateViewBinding<SearchSubreddit, ListItem, ItemSubredditBinding>(
    { inflater, root -> ItemSubredditBinding.inflate(inflater, root, false) }
) {
    bind {
        binding.subredditTitle.text = item.name
        binding.subredditDescription.text = item.description
        binding.subscribeButton.isSelected = false//item.isUserSubscriber == true
    }
    binding.subscribeButton.setOnClickListener {
        binding.subscribeButton.isSelected = !binding.subscribeButton.isSelected
        val action = if (!binding.subscribeButton.isSelected) UNSUBSCRIBE else SUBSCRIBE
        onClick(SubQuery(name = item.name, action = action), item, ClickableView.SUBSCRIBE)
    }

    binding.fullSubredditCard.setOnClickListener {
        onClick(SubQuery(id = item.id), item, ClickableView.SUBREDDIT)
    }
}