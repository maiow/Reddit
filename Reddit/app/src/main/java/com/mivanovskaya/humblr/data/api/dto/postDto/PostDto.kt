package com.mivanovskaya.humblr.data.api.dto.postDto

import com.mivanovskaya.humblr.data.api.dto.ThingDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostDto(
    override val kind: String,
    val data: PostDataDto,
) : ThingDto {
    @JsonClass(generateAdapter = true)
    data class PostDataDto(
        val subreddit: String,
        val selftext: String?,
        val author_fullname: String,
        val saved: Boolean,
        val title: String,
        val subreddit_name_prefixed: String,
        val name: String,
        val score: Int,
        val thumbnail: String?,
        val post_hint: String?,
        val created: Double,
        val url_overridden_by_dest: String?,
        val subreddit_id: String,
        val id: String,
        val author: String,
        val num_comments: Int,
        val permalink: String,
        val url: String,
        val media: Media?,
        val is_video: Boolean,
        val likes: Boolean?,
    )
}