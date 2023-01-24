package com.mivanovskaya.humblr.data.api.dto.commentDto

import com.mivanovskaya.humblr.domain.models.Comment
import com.google.gson.annotations.SerializedName
import com.mivanovskaya.humblr.data.api.dto.ThingDto

data class CommentDto(
    override val kind: String,
    val data: CommentDataDto
) : ThingDto {
    data class CommentDataDto(
        @SerializedName("subreddit_id")
        val subredditId: String?,
        val likes: Boolean?,
//        val replies: Any?,
        val saved: Boolean?,
        val id: String,
        val author: String,
        @SerializedName("parent_id")
        val parentId: String,
        val score: Int?,
        @SerializedName("author_fullname")
        val authorFullname: String?,
        val body: String?,
//        val edited: Boolean?,
        val name: String,
        val permalink: String?,
        val created: Double?,
        @SerializedName("link_id")
        val linkId: String?,
        @SerializedName("subreddit_name_prefixed")
        val subredditNamePrefixed: String?,
        val depth: Int?,
        val count: Int?,
        val children: List<String>?
    )

    fun toComment(): Comment {
//        data.replies as CommentListingDto
//        val replies = data.replies
        return Comment(
            id = data.id,
            name = data.name,
            likedByUser = data.likes,
//            replies = replies.toCommentListing(),
            author = data.author,
            score = data.score,
            body = data.body,
            permalink = data.permalink,
            created = data.created,
            linkId = data.linkId,
            subredditNamePrefixed = data.subredditNamePrefixed
        )
    }
}