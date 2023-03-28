package com.mivanovskaya.humblr.data.api.dto.postDto

import com.mivanovskaya.humblr.data.api.dto.ThingDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SinglePostListingDto(
    val kind: String,
    val data: SinglePostListingDataDto
) {
    @JsonClass(generateAdapter = true)
    data class SinglePostListingDataDto(
        val children: List<ThingDto>
    )
}
