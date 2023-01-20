package com.mivanovskaya.humblr.domain.repository

import com.mivanovskaya.humblr.domain.ListTypes
import com.mivanovskaya.humblr.domain.models.Subreddit
import com.mivanovskaya.humblr.domain.models.Subreddits

interface SubredditsRemoteRepository {

    suspend fun getList(type: ListTypes, source: String?/*, page: String*/): Subreddits//List<ListItem>

   // suspend fun votePost(dir: Int, postName: String)

    suspend fun getSubredditInfo(subredditName: String): Subreddit

}
