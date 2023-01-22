package com.mivanovskaya.humblr.presentation.home

import android.view.View
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.databinding.ItemPostImageBinding
import com.mivanovskaya.humblr.databinding.ItemSubredditBinding
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.models.Post
import com.mivanovskaya.humblr.domain.models.Subreddit
import com.mivanovskaya.humblr.domain.tools.ClickableView
import com.mivanovskaya.humblr.domain.tools.SUBSCRIBE
import com.mivanovskaya.humblr.domain.tools.SubQuery
import com.mivanovskaya.humblr.domain.tools.UNSUBSCRIBE
import com.mivanovskaya.humblr.tools.loadImage

fun subredditsDelegate(
    onClick: (subQuery: SubQuery, item: ListItem, clickableView: ClickableView) -> Unit,
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
        val action = if (!binding.subscribeButton.isSelected) UNSUBSCRIBE else SUBSCRIBE
        onClick(SubQuery(name = item.name, action = action), item, ClickableView.SUBSCRIBE)
    }

    binding.fullSubredditCard.setOnClickListener {
        onClick(SubQuery(id = item.id), item, ClickableView.SUBREDDIT)
    }
}

fun postsDelegate(
    onClick: (subQuery: SubQuery, item: ListItem, clickableView: ClickableView) -> Unit,
) = adapterDelegateViewBinding<Post, ListItem, ItemPostImageBinding>(
    { inflater, root -> ItemPostImageBinding.inflate(inflater, root, false) }
) {
    bind {
        binding.postTitle.text = item.title
        binding.commentsCount.text = item.numComments.toString()
        binding.subredditName.text = item.subredditNamePrefixed
        binding.userName.text = context.getString(R.string.author, item.author)
        if (item.postHint == "image") {
            binding.postBodyImage.apply {
                loadImage(item.url)
                visibility = View.VISIBLE
            }
        } else binding.postBodyImage.visibility = View.GONE
    }
}