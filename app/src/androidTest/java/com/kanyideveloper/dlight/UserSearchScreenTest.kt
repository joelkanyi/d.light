package com.kanyideveloper.dlight

import com.kanyideveloper.dlight.presentation.screens.search.UserSearchScreen
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.kanyideveloper.dlight.presentation.screens.search.UserSearchViewModel
import com.kanyideveloper.dlight.presentation.ui.theme.DlightTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4ClassRunner::class)
class UserSearchScreenTest {
/*    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var userSearchViewModel: UserSearchViewModel

    private val navigator = mock(DestinationsNavigator::class.java)


    @Test
    fun shows_gif_and_text_when_no_search_is_true() {
        composeTestRule.setContent {
            DlightTheme {
                UserSearchScreen(navigator = navigator, viewModel = userSearchViewModel)
            }
        }

        composeTestRule.onNodeWithContentDescription(label = "Empty State Gif").assertExists()
        composeTestRule.onNodeWithText("Search for a user to see their profile").assertExists()
    }*/
}