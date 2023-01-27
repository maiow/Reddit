package com.mivanovskaya.humblr.data.api

import com.mivanovskaya.humblr.data.api.dto.postDto.PostListingDto
import com.mivanovskaya.humblr.data.api.dto.postDto.SinglePostListingDto

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPost {

    @GET("/r/popular/{source}")
    suspend fun getPostListing(
        @Path("source") source: String?,
        @Query("after") page: String
    ): PostListingDto

    @POST("/api/vote")
    suspend fun votePost(
        @Query("dir") dir: Int,
        @Query("id") postName: String
    )

    @POST("/api/save")
    suspend fun savePost(
        @Query("id") postName: String
    )

    @POST("/api/unsave")
    suspend fun unsavePost(
        @Query("id") postName: String
    )

    //GET [/r/subreddit]/comments/article , where article = ID36 of a link
    //Get the comment tree for a given Link article.
    @GET("/comments/{url}/")
    suspend fun getSinglePost(
        @Path("url") url: String
    ): List<SinglePostListingDto>

    @GET("/user/{username}/saved/")
    suspend fun getSaved(
        @Path("username") username: String?,
        @Query("after") page: String
    ): PostListingDto
}