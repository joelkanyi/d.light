package com.kanyideveloper.dlight.domain.use_case

import com.kanyideveloper.dlight.domain.model.User
import com.kanyideveloper.dlight.domain.repository.UserRepository
import com.kanyideveloper.dlight.util.Resource
import kotlinx.coroutines.flow.Flow

class GetUserData(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String): Flow<Resource<User>> {

        return userRepository.getUser(username)
    }
}