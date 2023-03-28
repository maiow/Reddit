package com.mivanovskaya.humblr.data.api.dto.commentDto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentListingDto(
   val kind: String,
   val data: CommentListingDataDto
){
    @JsonClass(generateAdapter = true)
    data class CommentListingDataDto(
        val after: String?,
        val children: List<CommentDto>,
        val before: String?
    )
}