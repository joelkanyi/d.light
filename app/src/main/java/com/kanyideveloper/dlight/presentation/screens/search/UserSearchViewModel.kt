package com.kanyideveloper.dlight.presentation.screens.search

import androidx.compose.runtime.MutableState
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
import javax.inject.Inject

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
) : ViewModel() {

    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    private val _searchString = mutableStateOf("")
    val searchString: State<String> = _searchString

    fun setSearchString(value: String) {
        _searchString.value = value
    }

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    private val coroutineDispatcher = Dispatchers.IO

    private val _userDataState = mutableStateOf(UserDataState())
    val userDataState: State<UserDataState> = _userDataState

    fun getUserProfile(username: String) {
        viewModelScope.launch(coroutineDispatcher) {
            if (username.isEmpty()) {
                _eventFlow.emit(UiEvents.SnackbarEvent("Search field is empty, type something"))
                return@launch
            }

            _userDataState.value = userDataState.value.copy(
                isLoading = true
            )

            userUseCases.getUserData(username).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = true,
                            user = null,
                            followers = emptyList(),
                            following = emptyList(),
                            repos = emptyList(),
                            error = null
                        )
                    }
                    is Resource.Success -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = true,
                            user = result.data
                        )
                        getUserFollowings(username)
                        getUserFollowers(username)
                        getUserRepos(username)
                    }
                    is Resource.Error -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = false,
                            user = null,
                            followers = emptyList(),
                            following = emptyList(),
                            repos = emptyList(),
                            error = result.message
                        )
                        _eventFlow.emit(
                            UiEvents.SnackbarEvent(
                                message = result.message ?: "Unknown error occurred"
                            )
                        )
                    }
                }
            }
        }
    }

    private fun getUserFollowers(username: String) {
        viewModelScope.launch(coroutineDispatcher) {
            userUseCases.getUserFollowers(username).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = true,
                        )
                    }
                    is Resource.Success -> {
                        _userDataState.value = userDataState.value.copy(
                            followers = result.data ?: emptyList(),
                        )
                    }
                    is Resource.Error -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = false,
                            user = null,
                            followers = emptyList(),
                            following = emptyList(),
                            repos = emptyList(),
                            error = result.message
                        )
                        UiEvents.SnackbarEvent(
                            message = result.message ?: "Unknown error occurred"
                        )
                    }
                }
            }
        }
    }

    private fun getUserFollowings(username: String) {
        viewModelScope.launch(coroutineDispatcher) {
            userUseCases.getUserFollowings(username).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = true,
                        )
                    }
                    is Resource.Success -> {
                        _userDataState.value = userDataState.value.copy(
                            following = result.data ?: emptyList(),
                        )
                    }
                    is Resource.Error -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = false,
                            user = null,
                            followers = emptyList(),
                            following = emptyList(),
                            repos = emptyList(),
                            error = result.message
                        )
                        UiEvents.SnackbarEvent(
                            message = result.message ?: "Unknown error occurred"
                        )
                    }
                }
            }
        }
    }

    private fun getUserRepos(username: String) {
        viewModelScope.launch(coroutineDispatcher) {
            userUseCases.getUserRepos(username).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = true,
                        )
                    }
                    is Resource.Success -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = false,
                            repos = result.data ?: emptyList(),
                        )
                    }
                    is Resource.Error -> {
                        _userDataState.value = userDataState.value.copy(
                            isLoading = false,
                            user = null,
                            followers = emptyList(),
                            following = emptyList(),
                            repos = emptyList(),
                            error = result.message
                        )
                        UiEvents.SnackbarEvent(
                            message = result.message ?: "Unknown error occurred"
                        )
                    }
                }
            }
        }
    }
}