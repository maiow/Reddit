package com.mivanovskaya.humblr.data.state

//TODO: необходимо продумать использование всех load state во всех фрагментах,
// пока для экранов Профиля используется лишь error + другой вид стейтов с данными,
// отдельный для каждого экрана

enum class LoadState (var message:String="") {
    START,LOADING,ERROR,SUCCESS
}