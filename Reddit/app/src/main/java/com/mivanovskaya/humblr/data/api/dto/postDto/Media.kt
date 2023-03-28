package com.mivanovskaya.humblr.data.api.dto.postDto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    val reddit_video: RedditVideo?
) {
    @JsonClass(generateAdapter = true)
    data class RedditVideo(
        val bitrate_kbps: Int,
        val fallback_url: String,
        val height: Int,
        val width: Int,
        val scrubber_media_url: String,
        val dash_url: String,
        val duration: Int,
        val hls_url: String,
        val is_gif: Boolean,
        val transcoding_status: String
    )
}
