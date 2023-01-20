package com.mivanovskaya.humblr.data.api.dto.subredditDto

import com.google.gson.annotations.SerializedName

class SubredditDto(
    val kind: String,
    val data: SubredditDataDto,
)

class SubredditDataDto(
    val display_name: String?,
    val header_img: String?,
    val title: String?,
    val icon_img: String?,
    val display_name_prefixed: String,
    val subscribers: Int?,
    val name: String,
    val community_icon: String?,
    val banner_background_image: String?,
    val description_html: String?,
    val created: Double?,
    val user_is_subscriber: Boolean?,
    val public_description_html: String?,
    val banner_img: String?,
    val banner_background_color: String?,
    val id: String,
    val over18: Boolean?,
    @SerializedName("header_title")
    val description: String?,
    val url: String?,
    val mobile_banner_image: String?,
)

