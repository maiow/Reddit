package com.mivanovskaya.humblr.domain.tools

enum class ClickableView(val vote: Int = 0) {
    UP_VOTE(vote = 1),
    DOWN_VOTE(vote = -1),
    SAVE,
    PHOTO,
    POST_TITLE,
    COMMENT,
    SUBREDDIT,
    SUBSCRIBE
}
