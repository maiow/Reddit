package com.mivanovskaya.humblr.domain

class Change<T>(val value: T)

data class Query(
    var listing: ListTypes = ListTypes.POST,
    var source: String = "new"
)

data class FavoritesQuery(
    var listing: ListTypes = ListTypes.SAVED_POST,
    var source: String = ""
)