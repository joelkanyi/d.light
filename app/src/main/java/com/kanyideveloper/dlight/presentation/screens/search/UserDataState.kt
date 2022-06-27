package com.kanyideveloper.dlight.presentation.screens.search

import com.kanyideveloper.dlight.domain.model.Follow
import com.kanyideveloper.dlight.domain.model.Repo
import com.kanyideveloper.dlight.domain.model.User

data class UserDataState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null,
    val following: List<Follow> = emptyList(),
    val followers: List<Follow> = emptyList(),
    val repos: List<Repo> = emptyList(),
)
