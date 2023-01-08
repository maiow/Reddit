package com.mivanovskaya.humblr.data.api

import com.mivanovskaya.humblr.data.api.profileDto.ProfileDto
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiProfile {

    @GET("/api/v1/me")
    suspend fun getProfile(@Header("Authorization") Authorization: String? = "Bearer $TOKEN_SHARED_KEY",
                           //@Header("User-Agent") UserAgent: String? = "android:com.humblrmi:v1.0 (by Beginning_Android)"
                           ): ProfileDto

//    @GET("users/{username}/likes")
//    suspend fun getProfileLikes(
//        @Path("username") userName: String,
//        @Query("page") page: Int
//    ): PhotoListDto
}