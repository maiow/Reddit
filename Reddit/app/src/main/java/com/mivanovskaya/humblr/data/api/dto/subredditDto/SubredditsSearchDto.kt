package com.mivanovskaya.humblr.data.api.dto.subredditDto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditsSearchDto(
    val subreddits: List<SubredditSearchDto>
)

@JsonClass(generateAdapter = true)
data class SubredditSearchDto(
    val active_user_count: Int,
    val allow_chat_post_creation: Boolean,
    val allow_images: Boolean,
    val icon_img: String,
    val is_chat_post_feature_enabled: Boolean,
    val key_color: String,
    val name: String,
    val subscriber_count: Int
)