package com.mivanovskaya.humblr.presentation.home

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.mivanovskaya.humblr.databinding.ItemSubredditBinding
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.models.Subreddit

object HomeScreenDelegates {

    val subredditsDelegate = adapterDelegateViewBinding<Subreddit, ListItem, ItemSubredditBinding>(
        { inflater, root -> ItemSubredditBinding.inflate(inflater, root, false) }
    ) {
        bind {
            binding.subredditTitle.text = item.namePrefixed
            binding.subredditDescription.text = item.description
            binding.subscribeButton.isSelected = item.isUserSubscriber == true
            binding.subscribeButton.setOnClickListener {
                binding.subscribeButton.isSelected =! binding.subscribeButton.isSelected
                //TODO: добавить отправку запроса на сервер о подписке/отписке на сабреддит
//                onClickItem(
//                    Query(id = item.id)
//                )
            }
            //binding.executePendingBindings()
        }
    }
}