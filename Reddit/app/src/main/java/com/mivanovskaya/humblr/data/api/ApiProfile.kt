package com.mivanovskaya.humblr.data.api

import com.mivanovskaya.humblr.data.api.dto.postDto.SinglePostListingDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.ClickedUserProfileDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.FriendsListingDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.ProfileDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiProfile {

    @GET("/api/v1/me")
    suspend fun getLoggedUserProfile(): ProfileDto

    @GET("/api/v1/me/friends")
    suspend fun getFriends(): FriendsListingDto

    @GET("/user/{username}/about")
    suspend fun getAnotherUserProfile(
        @Path("username") username: String
    ): ClickedUserProfileDto

    @PUT("/api/v1/me/friends/{username}")
    suspend fun makeFriends(
        @Path("username") username: String,
        @Body requestBody: String = "{\"name\": \"$username\"}"
    )

    @GET("/user/{username}")
    suspend fun getUserContent(
        @Path("username") username: String
    ): SinglePostListingDto

/** no unfriend feature in tech.reqs, can add later*/
//    DELETE /api/v1/me/friends/usernamesubscribe
//    Stop being friends with a user.
}