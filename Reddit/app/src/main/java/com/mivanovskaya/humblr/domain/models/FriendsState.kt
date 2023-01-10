package com.mivanovskaya.humblr.domain.models

/**тут ли должен лежать?*/

sealed class FriendsState{
    data class Success(val data: Friendz): FriendsState()
    object NotStartedYet : FriendsState()
    object Loading : FriendsState()
}
