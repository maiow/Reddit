package com.mivanovskaya.humblr.tools

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mivanovskaya.humblr.R
import com.mivanovskaya.humblr.data.api.dto.profileDto.Children
import com.mivanovskaya.humblr.data.api.dto.profileDto.FriendsDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.FriendsWrapperDto
import com.mivanovskaya.humblr.domain.models.Friend
import com.mivanovskaya.humblr.domain.models.Friends
import com.mivanovskaya.humblr.domain.models.FriendsWrapper

fun FriendsWrapperDto.toFriendsWrapper() = FriendsWrapper(data = data.toFriends())

fun FriendsDto.toFriends() = Friends(friends_list = children.toListFriends())

fun List<Children>.toListFriends(): List<Friend> {
    return this.map { item -> item.toFriend() }
}

fun Children.toFriend() = Friend(id = id, name = name)

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

fun SearchView.setChangeTextListener(block: (query: String) -> Unit) {

    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(newText: String): Boolean {
            block(newText)
            return false
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }
    })
}
