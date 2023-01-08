package com.mivanovskaya.humblr.domain.repository

import com.mivanovskaya.humblr.domain.models.Profile


interface ProfileRemoteRepository {

    suspend fun getProfile(): Profile
}