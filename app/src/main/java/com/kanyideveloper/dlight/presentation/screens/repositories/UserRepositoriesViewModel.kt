package com.kanyideveloper.dlight.presentation.screens.repositories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.dlight.domain.use_case.UserUseCases
import com.kanyideveloper.dlight.presentation.screens.search.UserDataState
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
class UserRepositoriesViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    private val coroutineDispatcher = Dispatchers.IO

    private val _userReposState = mutableStateOf(UserDataState())
    val userReposState: State<UserDataState> = _userReposState

    private val _username = mutableStateOf("")
    val username: State<String?> = _username

    fun setUserName(value: String){
        _username.value = value
    }

    init {
        if (username.value.isNullOrEmpty()){
            username.value?.let { getUserRepos(it) }
        }
    }

    private fun getUserRepos(username: String) {
        viewModelScope.launch(coroutineDispatcher) {
            userUseCases.getUserRepos(username).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _userReposState.value = userReposState.value.copy(
                            isLoading = true,
                        )
                    }
                    is Resource.Success -> {
                        _userReposState.value = userReposState.value.copy(
                            isLoading = false,
                            repos = result.data ?: emptyList(),
                        )
                    }
                    is Resource.Error -> {
                        _userReposState.value = userReposState.value.copy(
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