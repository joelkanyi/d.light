package com.kanyideveloper.dlight.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun UserSearchScreen(
    viewModel: UserSearchViewModel = hiltViewModel()
) {

    val state by viewModel.userDataState
    val searchWidgetState by viewModel.searchWidgetState
    val searchString by viewModel.searchString

    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchStringState = searchString,
                onTextChange = {
                    viewModel.setSearchString(it)
                },
                onCloseClicked = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    viewModel.getUserProfile(it)
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        }
    ) {
        LazyColumn {
            item {
                if (state.user != null){
                    Text(text = state.user!!.name!!)
                }
            }
            item {
                Text(text = "Followers")
            }
            item{
                LazyRow{
                    items(state.followers) { follower ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .padding(10.dp),
                            elevation = 5.dp
                        ) {
                            Text(text = follower.login ?: "")
                        }
                    }
                }
            }
            item {
                Text(text = "Following")
            }
            item{
                LazyRow{
                    items(state.following) { follower ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .padding(10.dp),
                            elevation = 5.dp
                        ) {
                            Text(text = follower.login ?: "")
                        }
                    }
                }
            }
            item {
                Text(text = "Repos")
            }
            item{
                LazyRow{
                    items(state.repos) { follower ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .padding(10.dp),
                            elevation = 5.dp
                        ) {
                            Text(text = follower.name ?: "")
                        }
                    }
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}