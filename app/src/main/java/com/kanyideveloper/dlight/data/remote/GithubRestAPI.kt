package com.kanyideveloper.dlight.data.remote

import com.kanyideveloper.dlight.data.remote.dto.GithubUserResponseDto
import com.kanyideveloper.dlight.data.remote.dto.UserFollowersResponseDto
import com.kanyideveloper.dlight.data.remote.dto.UserFollowingResponseDto
import com.kanyideveloper.dlight.data.remote.dto.UserRepositoriesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubRestAPI {

    @GET("/users/{username}")
    suspend fun getUser(@Path("username") username: String): GithubUserResponseDto

    @GET("/users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): List<UserRepositoriesResponseDto>

    @GET("/users/{username}/followers")
    suspend fun getUserFollowers(@Path("username") username: String): List<UserFollowersResponseDto>

    @GET("/users/{username}/following")
    suspend fun getUserFollowing(@Path("username") username: String): List<UserFollowingResponseDto>

}