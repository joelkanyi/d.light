package com.kanyideveloper.dlight.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    val description: String?,
    val forksCount: Int?,
    val fullName: String?,
    val id: Int,
    val language: String?,
    val name: String?,
    val openIssuesCount: Int,
    val owner: Owner,
    val stargazersCount: Int,
    val updatedAt: String?
    ): Parcelable