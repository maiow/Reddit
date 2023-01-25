package com.mivanovskaya.humblr.data.api.dto.profileDto

import com.google.gson.annotations.SerializedName

data class ProfileDto(
    var name: String?,
    var display_name: String,
    var id: String?,
    @SerializedName("icon_img")
    var urlAvatar: String?,

    @SerializedName("subreddit")
    var more_infos: UserDataSubDto?,

//    var awarder_karma: Int?,
//    var awardee_karma: Int?,

//    @SerializedName("link_karma")
//    var publication_karma: Int?,

//    var comment_karma: Int?,
    var total_karma: Int?,

//    @SerializedName("created_utc")
//    var account_creation_date: Long?
)

data class UserDataSubDto(
    var subscribers: Int?,
//    @SerializedName("public_description")
//    var description: String?
)
