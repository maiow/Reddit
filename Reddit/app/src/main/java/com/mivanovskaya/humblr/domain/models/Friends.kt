package com.mivanovskaya.humblr.domain.models

import com.mivanovskaya.humblr.tools.ListItem

data class Friends(
    var friends_list: List<Friend>
)

data class Friend(
    val id: String,
    val name: String,
    val avatar_url: String = "https://styles.redditmedia.com/t5_7siqk8/styles/profileIcon_snooa0e9ec66-8c95-4891-a3e4-ba3daa482ac9-headshot.png"
) : ListItem
