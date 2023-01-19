package com.mivanovskaya.humblr.data.api.dto.profileDto

data class FriendsWrapperDto(
    //val data: Any,
    val data: FriendsDto,
    val kind: String
)
    /**пока не получается сделать универсальный KindDto с init, функции маппинга недоступны*/
   /*{ init {
         if (kind == "UserList") data is FriendsDto
         if (kind == "t3") data is LinkDto
         if (kind == "t1") data is CommentDto
         if (kind == "Listing") data is ListingDto
    }
}*/

data class FriendsDto(
    val children: List<Children>
)

data class Children(
    val id: String,
    val name: String,
    //val date: Double,
    //val rel_id: String
)
