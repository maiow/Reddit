package com.mivanovskaya.humblr.domain.models

class Profile(
    var name: String?,
    var id: String?,
    var urlAvatar: String?,
    var more_infos: UserDataSubscribers?,
    var total_karma: Int?,
)

class UserDataSubscribers(
    var subscribers: Int?,
    var title: String?
)
