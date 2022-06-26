package com.kanyideveloper.dlight.data.remote

import com.kanyideveloper.dlight.BuildConfig.ACCESS_TOKEN
import com.kanyideveloper.dlight.data.remote.dto.GithubUserResponseDto
import com.kanyideveloper.dlight.data.remote.dto.UserFollowResponseDto
import com.kanyideveloper.dlight.data.remote.dto.UserRepositoriesResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GithubRestAPI {

    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") username: String,
        @Header("Authorization") accessToken: String = ACCESS_TOKEN
    ): GithubUserResponseDto

    @GET("/users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String,
        @Header("Authorization") accessToken: String = ACCESS_TOKEN
    ): List<UserRepositoriesResponseDto>

    @GET("/users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") username: String,
        @Header("Authorization") accessToken: String = ACCESS_TOKEN
    ): List<UserFollowResponseDto>

    @GET("/users/{username}/following")
    suspend fun getUserFollowing(
        @Path("username") username: String,
        @Header("Authorization") accessToken: String = ACCESS_TOKEN
    ): List<UserFollowResponseDto>

}