package com.mivanovskaya.humblr.data.api.dto.subredditDto


data class SubredditListingDto(
    val kind: String,
    val data: SubredditListingDataDto,
)

data class SubredditListingDataDto(
    val after: String?,
    val children: List<SubredditDto>,
    val before: String?
)
