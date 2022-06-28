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
        allowForking = allowForking,
        blobsUrl = blobsUrl,
        branchesUrl = branchesUrl,
        cloneUrl = cloneUrl,
        collaboratorsUrl = collaboratorsUrl,
        commitsUrl = commitsUrl,
        contentsUrl = contentsUrl,
        contributorsUrl = contributorsUrl,
        description = description,
        forksCount = forksCount,
        forksUrl = forksUrl,
        fullName = fullName,
        gitCommitsUrl = gitCommitsUrl,
        gitUrl = gitUrl,
        id = id,
        language = language,
        name = name,
        openIssuesCount = openIssuesCount,
        `private` = `private`,
        pullsUrl = pullsUrl,
        size = size,
        stargazersCount = stargazersCount,
        svnUrl = svnUrl,
        topics = topics,
        url = url,
        visibility = visibility,
        owner = ownerDto.toDomain(),
        updatedAt = updatedAt
    )
}


internal fun OwnerDto.toDomain(): Owner {
    return Owner(
        avatarUrl = avatarUrl,
        eventsUrl = eventsUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        gravatarId = gravatarId,
        htmlUrl = htmlUrl,
        id = id,
        login = login,
        nodeId = nodeId,
        organizationsUrl = organizationsUrl,
        receivedEventsUrl = receivedEventsUrl,
        reposUrl = reposUrl,
        siteAdmin = siteAdmin,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        type = type,
        url = url
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