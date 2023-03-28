package com.mivanovskaya.humblr.domain.state

sealed interface LoadState {
    object NotStartedYet : LoadState
    object Loading : LoadState
    data class Content(var data: Any? = null, var data2: Any? = null) : LoadState
    data class Error(var message: String = "") : LoadState
}
