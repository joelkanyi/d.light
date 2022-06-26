package com.kanyideveloper.dlight.domain.use_case

data class UserUseCases(
    val getUserDataUseCase: GetUserData,
    val getUserFollowersUseCase: GetUserFollowers,
    val getUserFollowingUseCase: GetUserFollowings,
    val getUserReposUseCase: GetUserRepos,
)