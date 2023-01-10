package com.mivanovskaya.humblr.domain.repository

import com.mivanovskaya.humblr.domain.models.Friendz
import com.mivanovskaya.humblr.domain.models.Profile


interface ProfileRemoteRepository {

    suspend fun getProfile(): Profile
    suspend fun getFriends(): Friendz
}