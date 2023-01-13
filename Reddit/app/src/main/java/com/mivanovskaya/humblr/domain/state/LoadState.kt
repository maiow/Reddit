package com.mivanovskaya.humblr.domain.state

//TODO: пробовать c var data
/** Универсальный, сейчас используется только в AuthViewModel, где Content пустой.
 * Что добавить к data, чтобы можно было использовать во всех фрагментах, пока не смогла придумать,
 * в таком виде использовать не вышло - в фрагменте не достать данные из data*/

sealed class LoadState {
    object NotStartedYet : LoadState()
    object Loading : LoadState()
    data class Content(var data: Any?) : LoadState()
    data class Error(var message: String = "") : LoadState()
}
