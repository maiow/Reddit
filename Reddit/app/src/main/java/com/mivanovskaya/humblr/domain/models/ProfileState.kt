package com.mivanovskaya.humblr.domain.models

/**тут ли должен лежать?*/

sealed class ProfileState{
    data class Success(val data: Profile): ProfileState()
    object NotStartedYet : ProfileState()
    object Loading: ProfileState()
}
