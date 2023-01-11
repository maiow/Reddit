package com.mivanovskaya.humblr.domain.state

import com.mivanovskaya.humblr.domain.models.Profile

/**тут ли должен лежать?*/

sealed class ProfileState{
    data class Success(val data: Profile): ProfileState()
    object NotStartedYet : ProfileState()
    object Loading: ProfileState()
}
