package com.mivanovskaya.humblr.presentation.home

import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.tools.SubQuery
import com.mivanovskaya.humblr.presentation.ListItemDiffUtil
import com.mivanovskaya.humblr.tools.PagedDataDelegationAdapter

class HomePagedDataDelegationAdapter(
    private val onClick:(subQuery: SubQuery)-> Unit,
) : PagedDataDelegationAdapter<ListItem>(ListItemDiffUtil()) {
    init {
        delegatesManager.addDelegate(subredditsDelegate { onClick(it) })
    }
}