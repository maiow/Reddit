package com.mivanovskaya.humblr.data.api

import com.mivanovskaya.humblr.data.api.dto.profileDto.KindDto
import com.mivanovskaya.humblr.data.api.dto.profileDto.ProfileDto
import retrofit2.http.GET

interface ApiProfile {

    @GET("/api/v1/me")
    suspend fun getProfile(): ProfileDto

    @GET("/api/v1/me/friends")
    suspend fun getFriends(): KindDto
}