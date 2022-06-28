package com.kanyideveloper.dlight.presentation.screens.search

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.kanyideveloper.dlight.R
import com.kanyideveloper.dlight.domain.model.Follow
import com.kanyideveloper.dlight.domain.model.User
import com.kanyideveloper.dlight.presentation.components.MainAppBar
import com.kanyideveloper.dlight.presentation.components.SearchWidgetState
import com.kanyideveloper.dlight.presentation.screens.destinations.UserRepositoriesScreenDestination
import com.kanyideveloper.dlight.presentation.ui.theme.MyDarkGrayColor
import com.kanyideveloper.dlight.presentation.ui.theme.MyGrayColor
import com.kanyideveloper.dlight.util.UiEvents
import com.kanyideveloper.dlight.util.gifLoader
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Destination(start = true)
@Composable
fun UserSearchScreen(
    viewModel: UserSearchViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
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
                    viewModel.getUserProfile(it.trim())
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

                LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp)) {
                    item {
                        if (state.user != null) {
                            UserProfileHeader(
                                user = state.user,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(thickness = 0.7.dp, color = MyGrayColor)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${state.user?.publicRepos} Repositories",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black
                                    )
                                )
                                IconButton(
                                    onClick = {
                                        navigator.navigate(
                                            UserRepositoriesScreenDestination(
                                                username = viewModel.searchString.value
                                            )
                                        )
                                    },
                                ) {
                                    Icon(
                                        Icons.Default.ChevronRight,
                                        contentDescription = "To Repositories Screen"
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(thickness = 0.7.dp, color = MyGrayColor)

                        }
                    }

                    item {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Followers",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        )
                    }

                    item {
                        LazyRow {
                            items(state.followers) { follower ->
                                FollowItem(follow = follower)

                            }
                        }
                    }

                    item {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Following",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        )
                    }

                    item {
                        LazyRow {
                            items(state.following) { following ->
                                FollowItem(follow = following)
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
fun UserProfileHeader(
    user: User?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(
                            data = user?.avatarUrl
                                ?: "https://avatars.githubusercontent.com/u/5934628?v=4"
                        )
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                ),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = user?.name ?: user?.login ?: "Null",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = user?.login ?: "Null",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }

        if (user?.bio != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 1.dp,
                shape = RoundedCornerShape(
                    8.dp
                ),
                backgroundColor = MyGrayColor,
                border = BorderStroke(
                    0.3.dp,
                    Color.Black
                ),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = user.bio,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.PeopleOutline,
                contentDescription = "Followers Count",
                tint = MyDarkGrayColor,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "${user?.followers} followers")
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "${user?.following} following")

        }

        if (user?.company != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Icon(
                    imageVector = Icons.Outlined.BusinessCenter, contentDescription = "Company",
                    tint = MyDarkGrayColor,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${user.company}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }

        if (user?.location != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Icon(
                    imageVector = Icons.Outlined.LocationOn, contentDescription = "Location",
                    tint = MyDarkGrayColor,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${user.location}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }

        if (user?.email != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Icon(
                    imageVector = Icons.Outlined.Email, contentDescription = "Email",
                    tint = MyDarkGrayColor,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${user.email}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }

        if (user?.blog != null && user.blog != "") {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Icon(
                    imageVector = Icons.Outlined.Link, contentDescription = "Blog",
                    tint = MyDarkGrayColor,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${user.blog}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }

        if (user?.twitterUsername != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_twitter),
                    contentDescription = "Twitter",
                    tint = MyDarkGrayColor,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "@${user.twitterUsername}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Composable
fun FollowItem(
    follow: Follow,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = Color(0XFFf2f2f2),
        modifier = Modifier
            .width(180.dp)
            .padding(horizontal = 5.dp, vertical = 8.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(
            8.dp
        ),
    ) {
        Row(
            Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(
                            data = follow.avatarUrl
                                ?: "https://avatars.githubusercontent.com/u/5934628?v=4"
                        )
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                ),
                modifier = modifier
                    .clip(CircleShape)
                    .size(60.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${follow.login}",
                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
            )
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
            contentDescription = "Empty State Gif",
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