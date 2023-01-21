package com.mivanovskaya.humblr.domain.tools

class Change<T>(val value: T)

data class Query(
    var listing: ListTypes = ListTypes.SUBREDDIT,
    var source: String = "new"
)

data class FavoritesQuery(
    var listing: ListTypes = ListTypes.SAVED_POST,
    var source: String = ""
)