package com.mivanovskaya.humblr.data.api.dto.profileDto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ProfileDto(
    var name: String?,
    var id: String?,
    @Json(name = "icon_img")
    var urlAvatar: String?,

    @Json(name = "subreddit")
    var more_infos: UserDataSubDto?,
    var total_karma: Int?,

//    @SerializedName("created_utc")
//    var account_creation_date: Long?
) {
    @JsonClass(generateAdapter = true)
    class UserDataSubDto(
        var subscribers: Int?,
        @Json(name = "title")
        var title: String?
//    @SerializedName("public_description")
//    var description: String?
    )
}
