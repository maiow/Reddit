package com.mivanovskaya.humblr.domain.sharedpreferences

interface SharedPrefsService {

    fun saveQuery(query: String)

    fun getQuery(): String
}