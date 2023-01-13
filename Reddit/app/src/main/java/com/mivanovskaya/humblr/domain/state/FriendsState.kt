package com.mivanovskaya.humblr.domain.state

import com.mivanovskaya.humblr.domain.models.FriendsWrapper

/**тут ли должен лежать?*/

sealed class FriendsState {
    object NotStartedYet : FriendsState()
    object Loading : FriendsState()
    data class Content(val data: FriendsWrapper) : FriendsState()
    data class Error(var message: String = "") : FriendsState()
}
