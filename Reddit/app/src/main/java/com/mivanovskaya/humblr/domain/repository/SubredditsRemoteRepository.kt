package com.mivanovskaya.humblr.domain.repository

import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.tools.ListTypes
import com.mivanovskaya.humblr.domain.models.Subreddit

interface SubredditsRemoteRepository {

    suspend fun getList(type: ListTypes, source: String?, page: String): List<ListItem>//Subreddits//

    suspend fun subscribeOnSubreddit(action: String, name: String)

   // suspend fun votePost(dir: Int, postName: String)

    suspend fun getSubredditInfo(name: String): Subreddit

}
