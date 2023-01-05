package com.mivanovskaya.humblr.data.state

enum class LoadState (var message:String="") {
    START,LOADING,ERROR,SUCCESS
}