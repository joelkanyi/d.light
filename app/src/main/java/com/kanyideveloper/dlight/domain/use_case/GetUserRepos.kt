package com.kanyideveloper.dlight.domain.use_case

import com.kanyideveloper.dlight.domain.model.Repo
import com.kanyideveloper.dlight.domain.repository.UserRepository
import com.kanyideveloper.dlight.util.Resource
import kotlinx.coroutines.flow.Flow

class GetUserRepos(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String): Flow<Resource<List<Repo>>> {
        return userRepository.getUserRepos(username)
    }
}