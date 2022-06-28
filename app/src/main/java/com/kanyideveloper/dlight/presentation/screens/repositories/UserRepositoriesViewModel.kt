package com.kanyideveloper.dlight.presentation.screens.repositories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.dlight.domain.use_case.UserUseCases
import com.kanyideveloper.dlight.util.Resource
import com.kanyideveloper.dlight.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserRepositoriesViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val coroutineDispatcher = Dispatchers.Main

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    private val _userReposState = mutableStateOf(UserRepositoriesState())
    val userReposState: State<UserRepositoriesState> = _userReposState

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    fun setUserName(value: String) {
        _username.value = value
    }

    fun getUserRepositories(username: String) {
        Timber.d("get User repositories: $username")
        viewModelScope.launch(coroutineDispatcher) {
            _userReposState.value = userReposState.value.copy(
                isLoading = true,
            )
            userUseCases.getUserRepos(username).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        Timber.d("Loading")
                    }
                    is Resource.Success -> {
                        _userReposState.value = userReposState.value.copy(
                            isLoading = false,
                            repos = result.data ?: emptyList(),
                        )
                        Timber.d("Success")
                    }
                    is Resource.Error -> {
                        UiEvents.SnackbarEvent(
                            message = result.message ?: "Unknown error occurred"
                        )
                        _userReposState.value = userReposState.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                        Timber.d("Error")
                    }
                }
            }
        }
    }
}