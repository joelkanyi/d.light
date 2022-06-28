package com.kanyideveloper.dlight.data

import com.kanyideveloper.dlight.domain.model.Follow
import com.kanyideveloper.dlight.domain.model.Repo
import com.kanyideveloper.dlight.domain.model.User
import com.kanyideveloper.dlight.domain.repository.UserRepository
import com.kanyideveloper.dlight.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUserRepository : UserRepository {

    private val followers = mutableListOf<Follow>()
    private val following = mutableListOf<Follow>()
    private val repos = mutableListOf<Repo>()
    private val user = User(
        avatarUrl = "avatarUrl",
        bio = "bio",
        blog = "blog",
        company = "company",
        createdAt = "createdAt",
        email = "email",
        eventsUrl = "eventsUrl",
        followers = 0,
        followersUrl = "followersUrl",
        following = 0,
        followingUrl = "followingUrl",
        gistsUrl = "gistsUrl",
        gravatarId = "gravatarId",
        hireable = true,
        htmlUrl = "htmlUrl",
        id = 0,
        location = "location",
        login = "login",
        name = "name",
        nodeId = "nodeId",
        organizationsUrl = "organizationsUrl",
        publicGists = 2,
        publicRepos = 30,
        receivedEventsUrl = "",
        reposUrl = "",
        siteAdmin = false,
        starredUrl = "",
        subscriptionsUrl = "",
        twitterUsername = "",
        type = "",
        updatedAt = "",
        url = "",
    )

    override suspend fun getUser(username: String): Flow<Resource<User>> {
        return flowOf(Resource.Success(user))
    }

    override suspend fun getUserFollowers(username: String): Flow<Resource<List<Follow>>> {
        return flowOf(Resource.Success(followers))
    }

    override suspend fun getUserFollowing(username: String): Flow<Resource<List<Follow>>> {
        return flowOf(Resource.Success(following))
    }

    override suspend fun getUserRepos(username: String): Flow<Resource<List<Repo>>> {
        return flowOf(Resource.Success(repos))
    }
}
