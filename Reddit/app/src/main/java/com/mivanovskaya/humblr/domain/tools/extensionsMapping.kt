package com.mivanovskaya.humblr.domain.tools

import android.util.Log
import com.mivanovskaya.humblr.domain.models.Comment
import com.mivanovskaya.humblr.domain.models.Comments
import com.mivanovskaya.humblr.data.api.dto.commentDto.CommentDto
import com.mivanovskaya.humblr.data.api.dto.commentDto.CommentListingDto
import com.mivanovskaya.humblr.data.api.dto.postDto.PostDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.Children
import com.mivanovskaya.humblr.data.api.dto.profileDto.FriendsDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.ProfileDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.UserDataSubDto
import com.mivanovskaya.humblr.data.api.dto.subredditDto.SubredditDto
import com.mivanovskaya.humblr.data.api.dto.subredditDto.SubredditListingDataDto
import com.mivanovskaya.humblr.domain.models.*


fun CommentListingDto.toCommentListing() = Comments(
    children = data.children.toListComment()
)

fun List<CommentListingDto>.toListCommentListing(): List<Comments> =
    this.map { item -> item.toCommentListing() }

fun List<CommentDto>.toListComment(): List<Comment> =
    this.map { item -> item.toComment() }

fun ProfileDto.toProfile(): Profile {
    Log.e("Mapper: ", "${Profile(name, id, urlAvatar, more_infos?.toUserDataSub(), total_karma)}")
    return Profile(name, id, urlAvatar, more_infos?.toUserDataSub(), total_karma)
}

fun UserDataSubDto.toUserDataSub() = UserDataSubscribers(subscribers)

fun FriendsDto.toFriends() = Friends(friends_list = children.toListFriends())

fun List<Children>.toListFriends(): List<Friend> = this.map { item -> item.toFriend() }

fun Children.toFriend() = Friend(id = id, name = name)

fun SubredditListingDataDto.toSubreddits() =
    Subreddits(subreddits_list = children.toListSubreddit())

fun List<SubredditDto>.toListSubreddit(): List<Subreddit> = this.map { item -> item.toSubreddit() }

fun SubredditDto.toSubreddit() = Subreddit(
    namePrefixed = data.display_name_prefixed,
    url = data.url,
    isUserSubscriber = data.user_is_subscriber,
    description = data.description,
    subscribers = data.subscribers,
    created = data.created,
    id = data.id,
    name = data.name
)

fun List<PostDto>.toListPost(): List<Post> = this.map { item -> item.toPost() }

fun List<PostDto>.toListOfPostsNames(): List<String> = this.map { item -> item.toPostName() }

fun PostDto.toPostName(): String = data.name

fun PostDto.toPost(): Post {
    val voteDirection = when (data.likes) {
        null -> 0
        true -> 1
        false -> -1
    }
    return Post(
        selfText = data.selftext,
        authorFullname = data.author_fullname,
        saved = data.saved,
        title = data.title,
        subredditNamePrefixed = data.subreddit_name_prefixed,
        name = data.name,
        score = data.score,
        //        thumbnail = data.thumbnail,
        postHint = data.post_hint,
        created = data.created,
        //        urlOverriddenByDest = data.urlOverriddenByDest,
        //        subredditId = data.subredditId,
        id = data.id,
        author = data.author,
        numComments = data.num_comments,
        permalink = data.permalink,
        url = data.url,
        fallbackUrl = data.media?.reddit_video?.fallback_url,
        isVideo = data.is_video,
        likedByUser = data.likes,
        dir = voteDirection
    )
}
