package com.mivanovskaya.humblr.presentation.home

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateViewBindingViewHolder
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
        showScore(item.score)
        binding.postTitle.text = item.title
        //binding.commentsCount.text = item.numComments.toString()
        binding.subredditName.text = item.subredditNamePrefixed
        binding.userName.text = context.getString(R.string.author, item.author)
        if (item.postHint == "image") {
            binding.postBodyImage.apply {
                loadImage(item.url)
                visibility = View.VISIBLE
            }
        } else binding.postBodyImage.visibility = View.GONE

        binding.downloadButton.setOnClickListener {
            if (!binding.downloadButton.isSelected) {
                binding.downloadButton.isSelected = true
                Snackbar.make(binding.root, getString(R.string.downloaded), LENGTH_SHORT).show()
            } else Snackbar.make(binding.root, getString(R.string.already_downloaded), LENGTH_SHORT)
                .show()
        }

        binding.upVoteButton.isSelected = item.likedByUser == true

        binding.upVoteButton.setOnClickListener {
            if (!binding.upVoteButton.isSelected)
                onClick(SubQuery(dir = 1, name = item.name), item, ClickableView.UP_VOTE)
            else
                onClick(SubQuery(dir = 0, name = item.name), item, ClickableView.UP_VOTE)
            binding.upVoteButton.isSelected = !binding.upVoteButton.isSelected
            binding.downVoteButton.isSelected = false
            showScore(item.score)
        }

        binding.downVoteButton.isSelected = item.likedByUser == false
        binding.downVoteButton.setOnClickListener {
            if (!binding.downVoteButton.isSelected)
                onClick(SubQuery(dir = -1, name = item.name), item, ClickableView.DOWN_VOTE)
            else
                onClick(SubQuery(dir = 0, name = item.name), item, ClickableView.DOWN_VOTE)

            binding.downVoteButton.isSelected = !binding.downVoteButton.isSelected
            binding.upVoteButton.isSelected = false
            showScore(item.score)
        }

        binding.saveButton.setOnClickListener {
            onClick(SubQuery(), item, ClickableView.SAVE)
            binding.saveButton.isSelected = !binding.saveButton.isSelected
        }
    }
}

private fun AdapterDelegateViewBindingViewHolder<Post, ItemPostImageBinding>.showScore(score: Int) {
    if (score > 999_999) {
        binding.likes.text = getString(R.string.likesM, score / 1_000_000)
    } else {
        if (item.score > 999) binding.likes.text = getString(R.string.likesK, score / 1_000)
        else binding.likes.text = item.score.toString()
    }
}
