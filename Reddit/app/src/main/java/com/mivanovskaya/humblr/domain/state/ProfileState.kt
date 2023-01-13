package com.mivanovskaya.humblr.domain.state

import com.mivanovskaya.humblr.domain.models.Profile

/**тут ли должен лежать?*/

sealed class ProfileState {
    object NotStartedYet : ProfileState()
    object Loading : ProfileState()
    data class Content(val data: Profile) : ProfileState()
    data class Error(var message: String = "") : ProfileState()
}
