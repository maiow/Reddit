package com.mivanovskaya.humblr.domain.state

/** В Domain ли слое должен лежать?*/

sealed class LoadState {
    object NotStartedYet : LoadState()
    object Loading : LoadState()
    data class Content(var data: Any?/*, var data2: Any?*/) : LoadState()
    data class Content2(var data: Any?) : LoadState()
    data class Error(var message: String = "") : LoadState()
}
