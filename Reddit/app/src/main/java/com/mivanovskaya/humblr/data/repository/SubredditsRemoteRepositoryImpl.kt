package com.mivanovskaya.humblr.data.repository

import com.mivanovskaya.humblr.data.api.ApiPost
import com.mivanovskaya.humblr.data.api.ApiSubreddits
import com.mivanovskaya.humblr.domain.ListTypes
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.models.Subreddit
import com.mivanovskaya.humblr.domain.models.Subreddits
import com.mivanovskaya.humblr.domain.repository.SubredditsRemoteRepository
import com.mivanovskaya.humblr.tools.toListPost
import com.mivanovskaya.humblr.tools.toListSubreddit
import com.mivanovskaya.humblr.tools.toSubreddits
import javax.inject.Inject

class SubredditsRemoteRepositoryImpl @Inject constructor(
    private val apiSubreddits: ApiSubreddits,
    /*private val apiPost: ApiPost*/
) : SubredditsRemoteRepository {

    override suspend fun getList(type: ListTypes, source: String?/*, page: String*/): Subreddits {//List<ListItem> {
        return apiSubreddits.getSubredditListing(source/*, page*/).data.toSubreddits()
        /*  ListTypes.SUBREDDIT -> apiSubreddits.getSubredditListing(source, page)
                 .data.children.toListSubreddit()
            ListTypes.POST -> apiPost.getPostListing(source, page).data.children.toListPost()
             ListTypes.SUBREDDIT_POST -> apiSubreddits.getSubredditPosts(source, page)
                 .data.children.toListPost()
             ListTypes.SUBSCRIBED_SUBREDDIT -> apiSubreddits.getSubscribed(page)
                 .data.children.toListSubreddit()
             ListTypes.SAVED_POST -> apiPost.getSaved(source, page).data.children.toListPost()*/
    }
    //override suspend fun votePost(dir: Int, postName: String) = apiPost.votePost(dir, postName)

    override suspend fun getSubredditInfo(subredditName: String): Subreddit {
        TODO("Not yet implemented")
    }
}