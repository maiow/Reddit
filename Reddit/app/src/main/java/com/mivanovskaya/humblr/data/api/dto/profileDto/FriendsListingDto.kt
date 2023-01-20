package com.mivanovskaya.humblr.data.api.dto.profileDto

data class FriendsListingDto(
    val kind: String,
    val data: FriendsDto
)

data class FriendsDto(
    val children: List<Children>
)

data class Children(
    val id: String,
    val name: String,
    //val date: Double,
    //val rel_id: String
)
