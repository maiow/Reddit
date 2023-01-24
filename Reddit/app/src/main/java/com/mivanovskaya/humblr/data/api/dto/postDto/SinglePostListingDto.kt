package com.mivanovskaya.humblr.data.api.dto.postDto

import com.mivanovskaya.humblr.data.api.dto.ThingDto

data class SinglePostListingDto(
    val kind: String,
    val data: SinglePostListingDataDto
){
    data class SinglePostListingDataDto(
        val children: List<ThingDto>
    )
}
