package com.mivanovskaya.humblr.data.repository

import com.mivanovskaya.humblr.data.api.ApiPost
import com.mivanovskaya.humblr.data.api.ApiSubreddits
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.models.Subreddit
import com.mivanovskaya.humblr.domain.repository.SubredditsRemoteRepository
import com.mivanovskaya.humblr.domain.tools.ListTypes
import com.mivanovskaya.humblr.domain.tools.toListPost
import com.mivanovskaya.humblr.domain.tools.toListSubreddit
import com.mivanovskaya.humblr.domain.tools.toSubreddit
import javax.inject.Inject

class SubredditsRemoteRepositoryImpl @Inject constructor(
    private val apiSubreddits: ApiSubreddits,
    private val apiPost: ApiPost
) : SubredditsRemoteRepository {

    override suspend fun getList(type: ListTypes, source: String?, page: String): List<ListItem> {
        return when (type) {
            ListTypes.SUBREDDIT -> apiSubreddits.getSubredditListing(source, page)
                .data.children.toListSubreddit()

            ListTypes.SUBREDDIT_POST -> apiSubreddits.getSubredditPosts(source, page)
                .data.children.toListPost()
        }
            //ListTypes.POST -> apiPost.getPostListing(source, page).data.children.toListPost()
/*

             ListTypes.SUBSCRIBED_SUBREDDIT -> apiSubreddits.getSubscribed(page)
                 .data.children.toListSubreddit()
             ListTypes.SAVED_POST -> apiPost.getSaved(source, page).data.children.toListPost()*/
    }

    override suspend fun subscribeOnSubreddit(action: String, name: String) =
        apiSubreddits.subscribeOnSubreddit(action, name)

    override suspend fun votePost(dir: Int, postName: String) = apiPost.votePost(dir, postName)

    override suspend fun getSubredditInfo(name: String): Subreddit {
        return apiSubreddits.getSubredditInfo(name).toSubreddit()
    }
}