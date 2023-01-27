package com.mivanovskaya.humblr.presentation.home

import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.tools.ClickableView
import com.mivanovskaya.humblr.domain.tools.SubQuery
import com.mivanovskaya.humblr.presentation.ListItemDiffUtil
import com.mivanovskaya.humblr.presentation.delegates.postsDelegate
import com.mivanovskaya.humblr.presentation.delegates.subredditsDelegate
import com.mivanovskaya.humblr.tools.PagedDataDelegationAdapter

class HomePagedDataDelegationAdapter(
    private val onClick: (subQuery: SubQuery, item: ListItem, clickableView: ClickableView) -> Unit,
) : PagedDataDelegationAdapter<ListItem>(ListItemDiffUtil()) {
    init {
        delegatesManager.addDelegate(subredditsDelegate {
                subQuery: SubQuery, listItem: ListItem, clickableView: ClickableView ->
            onClick(subQuery, listItem, clickableView)
            }
        )
            .addDelegate(postsDelegate {
                subQuery: SubQuery, listItem: ListItem, clickableView: ClickableView ->
            onClick(subQuery, listItem, clickableView)
        }
        )
    }
}