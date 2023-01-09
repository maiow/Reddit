package com.mivanovskaya.humblr.domain.models

data class Profile(
    var name: String?,
    var id: String,
    //val icon_img: String,
    var urlProfilePic: String?,
    //var more_infos: UserDataSub?,
    var account_creation_date: Long?
)

data class UserDataSub(
    var subscribers: Int? = null,
    var description: String? = null
)
