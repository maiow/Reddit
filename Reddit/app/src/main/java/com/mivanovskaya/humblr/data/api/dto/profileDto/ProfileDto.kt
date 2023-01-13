package com.mivanovskaya.humblr.data.api.dto.profileDto

import com.google.gson.annotations.SerializedName
import com.mivanovskaya.humblr.domain.models.Profile
import com.mivanovskaya.humblr.domain.models.UserDataSubscribers

data class ProfileDto(
    var name: String?,
    var id: String,
    @SerializedName("icon_img")
    var urlAvatar: String?,

    @SerializedName("subreddit")
    var more_infos: UserDataSubDto?,

    var awarder_karma: Int?,
    var awardee_karma: Int?,

    @SerializedName("link_karma")
    var publication_karma: Int?,

    var comment_karma: Int?,
    var total_karma: Int?,

    @SerializedName("created_utc")
    var account_creation_date: Long?
) {
    fun toProfile() = Profile(name, id, urlAvatar, more_infos?.toUserDataSub(), total_karma)
}

data class UserDataSubDto(
    var subscribers: Int?,
    @SerializedName("public_description")
    var description: String?
) {
    fun toUserDataSub() = UserDataSubscribers(subscribers)
}
