package com.kanyideveloper.dlight.presentation.screens.search

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.kanyideveloper.dlight.R
import com.kanyideveloper.dlight.util.UiEvents
import com.kanyideveloper.dlight.util.gifLoader
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalComposeUiApi::class)
@Destination(start = true)
@Composable
fun UserSearchScreen(
    viewModel: UserSearchViewModel = hiltViewModel()
) {

    val state by viewModel.userDataState
    val searchWidgetState by viewModel.searchWidgetState
    val searchString by viewModel.searchString
    val scaffoldState = rememberScaffoldState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                else -> {}
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
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
                    keyboardController?.hide()
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        }
    ) {
        Box(Modifier.fillMaxSize()) {
            if (state.user != null && !state.isLoading) {
                LazyColumn {
                    item {
                        if (state.user != null) {
                            Text(text = state.user?.name ?: "Null")
                        }
                    }
                    item {
                        Text(text = "Followers")
                    }
                    item {
                        LazyRow {
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
                    item {
                        LazyRow {
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
                    item {
                        LazyRow {
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
            } else if (state.user == null && !state.isLoading) {
                EmptyStateGifImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .align(Alignment.Center),
                    context = context,
                )
            }
            if (state.isLoading) {
                LoadingGif(
                    context = context,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun LoadingGif(
    context: Context,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.octocat).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = context.gifLoader()
        ),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
    )
}

@Composable
fun EmptyStateGifImage(
    modifier: Modifier = Modifier,
    context: Context
) {

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = R.drawable.search).apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = context.gifLoader()
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )
        Text(
            text = "Search for a user to see their profile",
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}