package com.mivanovskaya.humblr.data.api.dto.profileDto

import com.mivanovskaya.humblr.domain.models.Friend
import com.mivanovskaya.humblr.domain.models.Friends
import com.mivanovskaya.humblr.domain.models.Friendz
import com.mivanovskaya.humblr.tools.toListFriends

data class KindDto(
    //val data: Any,
    val data: FriendsDto,
    val kind: String
) {
/*    init {
        if (kind == "UserList") data is FriendsDto
        // if (kind == "t3") data is LinkDto
        // if (kind == "t1") data is CommentDto
        // if (kind == "Listing") data is ListingDto
    }*/
    fun toFriendz() = Friendz(data = data.toFriends(), kind = kind)
}

data class FriendsDto(
    val children: List<Children>
) {
    fun toFriends() = Friends(friends_list = children.toListFriends())
}

data class Children(
    val date: Double,
    val id: String,
    val name: String,
    // val rel_id: String
){
    fun toFriend() = Friend(id = id, name = name)
}
