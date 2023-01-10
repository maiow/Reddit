package com.mivanovskaya.humblr.domain.models

data class Friends(
    var friends_list: List<Friend>
)

data class Friend(
    val id: String,
    val name: String
)
