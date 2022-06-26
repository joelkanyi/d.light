package com.kanyideveloper.dlight.domain.repository

import com.kanyideveloper.dlight.domain.model.*
import com.kanyideveloper.dlight.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(username: String): Flow<Resource<User>>
    suspend fun getUserFollowers(username: String): Flow<Resource<List<Follow>>>
    suspend fun getUserFollowing(username: String): Flow<Resource<List<Follow>>>
    suspend fun getUserRepos(username: String): Flow<Resource<List<Repo>>>
}