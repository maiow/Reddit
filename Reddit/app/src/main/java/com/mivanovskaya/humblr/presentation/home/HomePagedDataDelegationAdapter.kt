package com.mivanovskaya.humblr.presentation.home

import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.presentation.ListItemDiffUtil
import com.mivanovskaya.humblr.tools.PagedDataDelegationAdapter

class HomePagedDataDelegationAdapter(
    //private val onClick:(item: ListItem)->Unit
) : PagedDataDelegationAdapter<ListItem>(ListItemDiffUtil()) {
    init {
        delegatesManager.addDelegate(HomeScreenDelegates.subredditsDelegate /*{ onClick(it) }*/)
    }
}