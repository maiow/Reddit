package com.mivanovskaya.humblr.domain.models

data class Profile(
    var name: String?,
    var id: String,
    var urlAvatar: String?,
    var more_infos: UserDataSubscribers?,
    var total_karma: Int?,
)

data class UserDataSubscribers(
    var subscribers: Int?
)
