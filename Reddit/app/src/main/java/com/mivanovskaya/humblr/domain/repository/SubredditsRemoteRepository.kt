package com.mivanovskaya.humblr.domain.repository

import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.tools.ListTypes
import com.mivanovskaya.humblr.domain.models.Subreddit

interface SubredditsRemoteRepository {

    suspend fun getList(type: ListTypes, source: String?, page: String): List<ListItem>

    suspend fun subscribeOnSubreddit(action: String, name: String)

    suspend fun votePost(dir: Int, postName: String)

    suspend fun savePost(postName: String)

    suspend fun unsavePost(postName: String)

    suspend fun getSubredditInfo(name: String): Subreddit

    suspend fun getSinglePost(url: String): List<ListItem>

    suspend fun unsaveAllSavedPosts()
}
