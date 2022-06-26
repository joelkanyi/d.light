package com.kanyideveloper.dlight.domain.use_case

import com.kanyideveloper.dlight.domain.model.Follow
import com.kanyideveloper.dlight.domain.repository.UserRepository
import com.kanyideveloper.dlight.util.Resource
import kotlinx.coroutines.flow.Flow

class GetUserFollowings(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String): Flow<Resource<List<Follow>>> {
        return userRepository.getUserFollowing(username)
    }
}