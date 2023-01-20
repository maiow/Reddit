package com.mivanovskaya.humblr.domain.models

import com.mivanovskaya.humblr.domain.ListItem

data class Post(
    val selfText: String?,
    val authorFullname: String,
    val saved: Boolean,
    val title: String,
    val subredditNamePrefixed: String,
    override val name: String,
    val score: Int,
//    val thumbnail: String?,
    val postHint: String?,
    val created: Double,
//    val urlOverriddenByDest: String?,
//    val subredditId: String,
    override val id: String,
    val author: String,
    val numComments: Int,
    val permalink: String,
    val url: String,
    val fallbackUrl: String?,
    val isVideo: Boolean,
    val likedByUser: Boolean?,
    var dir: Int
): ListItem
