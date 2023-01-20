package com.mivanovskaya.humblr.tools

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.tabs.TabLayout
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.data.api.dto.postDto.PostDataDto
import com.mivanovskaya.humblr.data.api.dto.postDto.PostDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.Children
import com.mivanovskaya.humblr.data.api.dto.profileDto.FriendsDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.ProfileDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.UserDataSubDto
import com.mivanovskaya.humblr.data.api.dto.subredditDto.SubredditDto
import com.mivanovskaya.humblr.data.api.dto.subredditDto.SubredditListingDataDto
import com.mivanovskaya.humblr.domain.models.*

fun ProfileDto.toProfile() = Profile(name, id, urlAvatar, more_infos?.toUserDataSub(), total_karma)

fun UserDataSubDto.toUserDataSub() = UserDataSubscribers(subscribers)

fun FriendsDto.toFriends() = Friends(friends_list = children.toListFriends())

fun List<Children>.toListFriends(): List<Friend> = this.map { item -> item.toFriend() }

fun Children.toFriend() = Friend(id = id, name = name)

fun SubredditListingDataDto.toSubreddits() = Subreddits(subreddits_list = children.toListSubreddit())

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


fun PostDto.toPost(): Post {
    val dir = when (data.likes) {
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
        dir = dir
    )
}

fun ImageView.loadImage(urls: String) {
    Glide.with(this)
        .load(urls)
        .error(R.drawable.error_image)
        // без crop смотрится лучше и ближе к дизайну оригинального приложения reddit
        //.circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun ImageView.loadImage(id: Int) {
    Glide.with(this)
        .load(id)
        .error(R.drawable.error_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun TabLayout.setSelectedTabListener(block: (position: Int) -> Unit){
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if (tab != null) { block(tab.position) }
        }
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}

//fun SearchView.setChangeTextListener(block: (query: String) -> Unit) {
//
//    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//
//        override fun onQueryTextChange(newText: String): Boolean {
//            block(newText)
//            return false
//        }
//
//        override fun onQueryTextSubmit(query: String): Boolean {
//            return false
//        }
//    })
//}
