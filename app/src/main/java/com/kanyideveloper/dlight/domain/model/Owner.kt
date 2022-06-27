package com.kanyideveloper.dlight.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val avatarUrl: String?,
    val eventsUrl: String?,
    val followersUrl: String?,
    val followingUrl: String?,
    val gistsUrl: String?,
    val gravatarId: String?,
    val htmlUrl: String?,
    val id: Int?,
    val login: String?,
    val nodeId: String?,
    val organizationsUrl: String?,
    val receivedEventsUrl: String?,
    val reposUrl: String?,
    val siteAdmin: Boolean,
    val starredUrl: String?,
    val subscriptionsUrl: String?,
    val type: String?,
    val url: String?
): Parcelable
