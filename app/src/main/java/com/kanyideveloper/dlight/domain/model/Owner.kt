package com.kanyideveloper.dlight.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val avatarUrl: String?,
    val id: Int?,
    val login: String?,
    val type: String?,
): Parcelable
