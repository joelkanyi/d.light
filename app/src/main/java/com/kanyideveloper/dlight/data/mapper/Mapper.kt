package com.kanyideveloper.dlight.data.mapper

import com.kanyideveloper.dlight.data.local.entity.UserEntity
import com.kanyideveloper.dlight.data.remote.dto.GithubUserResponseDto
import com.kanyideveloper.dlight.data.remote.dto.OwnerDto
import com.kanyideveloper.dlight.data.remote.dto.UserFollowResponseDto
import com.kanyideveloper.dlight.data.remote.dto.UserRepositoriesResponseDto
import com.kanyideveloper.dlight.domain.model.Follow
import com.kanyideveloper.dlight.domain.model.Owner
import com.kanyideveloper.dlight.domain.model.Repo
import com.kanyideveloper.dlight.domain.model.User


internal fun UserRepositoriesResponseDto.toDomain(): Repo {
    return Repo(
        description = description,
        forksCount = forksCount,
        fullName = fullName,
        id = id,
        language = language,
        name = name,
        openIssuesCount = openIssuesCount,
        stargazersCount = stargazersCount,
        owner = ownerDto.toDomain(),
        updatedAt = updatedAt
    )
}


internal fun OwnerDto.toDomain(): Owner {
    return Owner(
        avatarUrl = avatarUrl,
        id = id,
        login = login,
        type = type,
    )
}

internal fun UserFollowResponseDto.toDomain(): Follow {
    return Follow(
        avatarUrl = avatarUrl,
        eventsUrl = eventsUrl,
        id = id,
        login = login,
        nodeId = nodeId,
        type = type,
        url = url
    )
}

internal fun UserEntity.toDomain(): User {
    return User(
        avatarUrl = avatarUrl,
        bio = bio,
        blog = blog,
        company = company,
        createdAt = createdAt,
        email = email,
        eventsUrl = eventsUrl,
        followers = followers,
        followersUrl = followersUrl,
        following = following,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        gravatarId = gravatarId,
        hireable = hireable,
        htmlUrl = htmlUrl,
        id = id,
        location = location,
        login = login,
        name = name,
        nodeId = nodeId,
        organizationsUrl = organizationsUrl,
        publicGists = publicGists,
        publicRepos = publicRepos,
        receivedEventsUrl = receivedEventsUrl,
        reposUrl = reposUrl,
        siteAdmin = siteAdmin,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        twitterUsername = twitterUsername,
        type = type,
        updatedAt = updatedAt,
        url = url,
    )
}

internal fun GithubUserResponseDto.toEntity(): UserEntity {
    return UserEntity(
        avatarUrl = avatarUrl,
        bio = bio,
        blog = blog,
        company = company,
        createdAt = createdAt,
        email = email,
        eventsUrl = eventsUrl,
        followers = followers,
        followersUrl = followersUrl,
        following = following,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        gravatarId = gravatarId,
        hireable = hireable,
        htmlUrl = htmlUrl,
        id = id,
        location = location,
        login = login,
        name = name,
        nodeId = nodeId,
        organizationsUrl = organizationsUrl,
        publicGists = publicGists,
        publicRepos = publicRepos,
        receivedEventsUrl = receivedEventsUrl,
        reposUrl = reposUrl,
        siteAdmin = siteAdmin,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        twitterUsername = twitterUsername,
        type = type,
        updatedAt = updatedAt,
        url = url,
    )
}