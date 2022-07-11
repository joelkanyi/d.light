package com.kanyideveloper.dlight.util

import com.kanyideveloper.dlight.domain.model.Follow
import com.kanyideveloper.dlight.domain.model.Owner
import com.kanyideveloper.dlight.domain.model.Repo
import com.kanyideveloper.dlight.domain.model.User

object TestData {
    val testFollowers = mutableListOf(
        Follow(
            login = "esin",
            id = 69767,
            nodeId = "MDQ6VXNlcjY5NzY3",
            avatarUrl = "https://avatars.githubusercontent.com/u/69767?v=4",
            url = "https://api.github.com/users/esin",
            type = "User",
            eventsUrl = "https://api.github.com/users/esin/events{/privacy}"
        ),
        Follow(
            login = "mcchots",
            id = 975793,
            nodeId = "MDQ6VXNlcjk3NTc5Mw==",
            avatarUrl = "https://avatars.githubusercontent.com/u/975793?v=4",
            url = "https://api.github.com/users/mcchots",
            type = "User",
            eventsUrl = "https://api.github.com/users/mcchots/events{/privacy}"
        )
    )


    val testFollowing = mutableListOf(
        Follow(
            login = "gradle",
            id = 124156,
            nodeId = "MDEyOk9yZ2FuaXphdGlvbjEyNDE1Ng==",
            avatarUrl = "https://avatars.githubusercontent.com/u/124156?v=4",
            url = "https://api.github.com/users/gradle",
            type = "Organization",
            eventsUrl = "https://api.github.com/users/gradle/events{/privacy}"
        ),
        Follow(
            login = "kigen",
            id = 228888,
            nodeId = "MDQ6VXNlcjIyODg4OA==",
            avatarUrl = "https://avatars.githubusercontent.com/u/228888?v=4",
            url = "https://api.github.com/users/kigen",
            type = "User",
            eventsUrl = "https://api.github.com/users/kigen/events{/privacy}"
        )
    )

    val testRepos = mutableListOf(
        Repo(
            description = "Options Menu, Floating Contextual Menu, ActionMode Contextual Menu and Popup Menu",
            forksCount = 0,
            fullName = "JoelKanyi/Android-Menus",
            id = 279129885,
            language = "Java",
            name = "Android-Menus",
            openIssuesCount = 0,
            stargazersCount = 1,
            owner = Owner(
                avatarUrl = "https://avatars.githubusercontent.com/u/50293753?v=4",
                id = 50293753,
                login = "JoelKanyi",
                type = "User",
            ),
            updatedAt = "2020-07-12T19:58:40Z"
        )
    )
    val testUser = User(
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
        id = 50293753,
        location = "location",
        login = "JoelKanyi",
        name = "Joel Kanyi",
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
}