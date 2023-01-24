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

    @GET("/comments/{url}/")
    suspend fun getSinglePost(
        @Path("url") url: String
    ): List<SinglePostListingDto>

    @GET("/user/{user_name}/saved/")
    suspend fun getSaved(
        @Path("user_name") userName: String?,
        @Query("after") page: String
    ): PostListingDto
}