package com.kanyideveloper.dlight.presentation.screens.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.kanyideveloper.dlight.di.AppModule
import com.kanyideveloper.dlight.di.UserModule
import com.kanyideveloper.dlight.presentation.MainActivity
import com.kanyideveloper.dlight.presentation.screens.NavGraphs
import com.kanyideveloper.dlight.presentation.screens.destinations.UserSearchScreenDestination
import com.kanyideveloper.dlight.presentation.ui.theme.DlightTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@HiltAndroidTest
@UninstallModules(AppModule::class, UserModule::class)
class UserSearchScreenTest {
    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navigator = mock(DestinationsNavigator::class.java)
            DlightTheme {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    startRoute = UserSearchScreenDestination
                ) {
                    composable(destination = UserSearchScreenDestination) {
                        UserSearchScreen(navigator = navigator)
                    }
                }
            }
        }
    }

    @Test
    fun clickSearchIcon_toggles_from_DefaultAppBar_to_SearchAppBar(){

        composeRule.onNodeWithTag("DefaultAppBar").assertIsDisplayed()
        composeRule.onNodeWithTag("SearchAppBar").assertDoesNotExist()

        composeRule.onNodeWithContentDescription("Default Search Icon").performClick()

        composeRule.onNodeWithTag("SearchAppBar").assertIsDisplayed()

    }

    @Test
    fun toggles_from_SearchAppBar_to_DefaultAppBar(){

        composeRule.onNodeWithTag("SearchAppBar").assertDoesNotExist()

        composeRule.onNodeWithContentDescription("Default Search Icon").performClick()

        composeRule.onNodeWithTag("SearchAppBar").assertIsDisplayed()

        composeRule.onNodeWithContentDescription("Close Icon").performClick()

        composeRule.onNodeWithTag("SearchAppBar").assertDoesNotExist()

        composeRule.onNodeWithTag("DefaultAppBar").assertIsDisplayed()
    }
}