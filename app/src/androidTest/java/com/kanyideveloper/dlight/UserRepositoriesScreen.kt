package com.kanyideveloper.dlight

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.kanyideveloper.dlight.presentation.MainActivity
import com.kanyideveloper.dlight.presentation.screens.repositories.UserRepositoriesScreen
import com.kanyideveloper.dlight.presentation.ui.theme.DlightTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4ClassRunner::class)
class UserRepositoriesScreenTest {
/*    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val navigator = mock(DestinationsNavigator::class.java)

    @ExperimentalCoroutinesApi
    @Test
    fun top_bar_is_visible(){
        composeTestRule.setContent {
            DlightTheme {
                UserRepositoriesScreen("JoelKanyi",navigator = navigator)
            }
        }
        composeTestRule.onNodeWithText("Repositories").assertExists()
    }*/
}
