package com.mivanovskaya.humblr.domain.models

import com.mivanovskaya.humblr.domain.ListItem

data class Comments(
    val children : List<Comment>
)

data class Comment(
    override val id: String,
    override val name: String,
    val likedByUser: Boolean?,
//    val replies: CommentListing?,
//    val saved: Boolean?,
    val author: String,
//    val parentId: String,
    val score: Int?,
//    val authorFullname: String?,
    val body: String?,
//    val edited: Boolean?,
    val permalink: String?,
    val created: Double?,
    val linkId: String?,
    val subredditNamePrefixed: String?,
//    val depth: Int,
//    val count: Int?,
//    val children: List<String>?
) : ListItem
