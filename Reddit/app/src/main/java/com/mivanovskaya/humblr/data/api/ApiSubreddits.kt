package com.mivanovskaya.humblr.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//interface ApiSubreddits {
//
//    @GET("/subreddits/{source}")
//    suspend fun getSubredditListing(
//        @Path("source") source: String?,
//        @Query("after") page: String
//    ): SubredditListingDto
//
//    @GET("/{source}")
//    suspend fun getSubredditPosts(
//        @Path("source") source: String?,
//        @Query("after") page: String
//    ): PostListingDto
//
//    @GET("/{source}/about")
//    suspend fun getSubredditInfo(
//        @Path("source") source: String?
//    ): SubredditDto
//
//    @GET("/subreddits/mine/subscriber")
//    suspend fun getSubscribed(
//        @Query("after") page: String?
//    ): SubredditListingDto
//}