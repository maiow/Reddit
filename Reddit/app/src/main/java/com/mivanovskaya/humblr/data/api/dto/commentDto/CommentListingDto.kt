package com.mivanovskaya.humblr.data.api.dto.commentDto


import com.mivanovskaya.humblr.domain.models.CommentListing
import com.mivanovskaya.humblr.domain.tools.toListComment

data class CommentListingDto(
   val kind: String,
   val data: CommentListingDataDto
){
    data class CommentListingDataDto(
        val after: String?,
        val children: List<CommentDto>,
        val before: String?
    )

    fun toCommentListing() = CommentListing(
        children = data.children.toListComment()
    )
}