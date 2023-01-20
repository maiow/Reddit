package com.mivanovskaya.humblr.presentation

import com.mivanovskaya.humblr.domain.ClickableView
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.presentation.home.HomeScreenDelegates
import com.mivanovskaya.humblr.tools.PagedDataDelegationAdapter

/*
class ThingPagingAdapter(
    private val onClick: (ClickableView, ListItem) -> Unit
) : PagedDataDelegationAdapter<ListItem>(ListItemDiffUtil()) {
    init {
        delegatesManager.addDelegate(HomeScreenDelegates.subredditsDelegate { onClick(it)})
    }
}

class OneCategoryForPaging(
    private val onClickFilm:(pageCategory: PageCategory)->Unit
) : PagedDataDelegationAdapter<NestedInfoInCategory>(NestedItemDiffUtil()) {
    init {
        delegatesManager.addDelegate(delegateBaseFilms { onClickFilm(it) })
            .addDelegate(delegateStaffForCategoryPage{ onClickFilm(it) })
    }
}*/
