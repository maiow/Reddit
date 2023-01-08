package com.mivanovskaya.humblr.data.repository

import com.mivanovskaya.humblr.data.api.ApiProfile
import com.mivanovskaya.humblr.data.api.interceptor.AuthTokenProvider
import com.mivanovskaya.humblr.domain.models.Profile
import com.mivanovskaya.humblr.domain.repository.ProfileRemoteRepository
import javax.inject.Inject

class ProfileRemoteRepositoryImpl @Inject constructor(private val apiProfile: ApiProfile, private val tokenProvider: AuthTokenProvider):
    ProfileRemoteRepository {

    override suspend fun getProfile(): Profile = apiProfile.getProfile("Bearer "+tokenProvider.getToken()).toProfile()
}