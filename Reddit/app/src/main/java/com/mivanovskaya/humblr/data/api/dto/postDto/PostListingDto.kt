package com.mivanovskaya.humblr.data.api.dto.postDto

data class PostListingDto(
    val kind: String,
    val data: PostListingDataDto
)

data class PostListingDataDto(
    val after: String?,
    val children: List<PostDto>,
    val before: Any?
)
