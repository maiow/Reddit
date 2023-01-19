package com.mivanovskaya.humblr.data.repository

import com.mivanovskaya.humblr.data.api.ApiProfile
import com.mivanovskaya.humblr.domain.models.FriendsWrapper
import com.mivanovskaya.humblr.domain.models.Profile
import com.mivanovskaya.humblr.domain.repository.ProfileRemoteRepository
import com.mivanovskaya.humblr.tools.toFriendsWrapper
import javax.inject.Inject

class ProfileRemoteRepositoryImpl @Inject constructor(private val apiProfile: ApiProfile):
    ProfileRemoteRepository {

    override suspend fun getProfile(): Profile = apiProfile.getProfile().toProfile()

    override suspend fun getFriends(): FriendsWrapper = apiProfile.getFriends().toFriendsWrapper()
}