package com.mivanovskaya.humblr.domain.models

import com.mivanovskaya.humblr.domain.ListItem

data class SearchSubreddits(
    val subreddits: List<SearchSubreddit>
)

data class SearchSubreddit(
    val icon_img: String,
    val subscribers: Int,
    val description: String = "",
    override val id: String, //no id from api
    override val name: String
): ListItem