package com.mivanovskaya.humblr.domain.state

import com.mivanovskaya.humblr.domain.models.FriendsWrapper

/**тут ли должен лежать?*/

sealed class FriendsState{
    data class Success(val data: FriendsWrapper): FriendsState()
    object NotStartedYet : FriendsState()
    object Loading : FriendsState()
}
