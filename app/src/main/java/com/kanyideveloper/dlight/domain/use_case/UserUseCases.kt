package com.kanyideveloper.dlight.domain.use_case

data class UserUseCases(
    val getUserData: GetUserData,
    val getUserFollowers: GetUserFollowers,
    val getUserFollowings: GetUserFollowings,
    val getUserRepos: GetUserRepos,
)