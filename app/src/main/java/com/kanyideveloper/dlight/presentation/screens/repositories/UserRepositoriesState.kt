package com.kanyideveloper.dlight.presentation.screens.repositories

import com.kanyideveloper.dlight.domain.model.Repo

data class UserRepositoriesState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val repos: List<Repo> = emptyList(),
)
