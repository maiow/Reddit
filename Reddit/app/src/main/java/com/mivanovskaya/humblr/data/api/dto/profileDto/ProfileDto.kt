package com.mivanovskaya.humblr.data.api.dto.profileDto

import com.google.gson.annotations.SerializedName
import com.mivanovskaya.humblr.domain.models.Profile

data class ProfileDto(
    var name: String?,

    var id: String,
    //val icon_img: String,
    @SerializedName("icon_img")
    var urlProfilePic: String?,

    @SerializedName("subreddit")
    var more_infos: UserDataSub?,

    @SerializedName("awarder_karma")
    var awarder_karma: Int?,

    @SerializedName("awardee_karma")
    var awardee_karma: Int?,

    @SerializedName("link_karma")
    var publication_karma: Int?,

    @SerializedName("comment_karma")
    var comment_karma: Int?,

    @SerializedName("total_karma")
    var total_karma: Int?,

    @SerializedName("created_utc")
    var account_creation_date: Long?
) {
    fun toProfile() = Profile(name, id, urlProfilePic, total_karma)
}

data class UserDataSub(
    @SerializedName("subscribers")
    var subscribers: Int?,

    @SerializedName("public_description")
    var description: String?
)
