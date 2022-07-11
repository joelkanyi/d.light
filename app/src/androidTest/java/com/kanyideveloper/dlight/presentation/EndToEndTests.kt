package com.kanyideveloper.dlight.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.kanyideveloper.dlight.di.AppModule
import com.kanyideveloper.dlight.di.UserModule
import com.kanyideveloper.dlight.presentation.screens.NavGraphs
import com.kanyideveloper.dlight.presentation.screens.destinations.UserSearchScreenDestination
import com.kanyideveloper.dlight.presentation.screens.search.UserSearchScreen
import com.kanyideveloper.dlight.presentation.ui.theme.DlightTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@HiltAndroidTest
@UninstallModules(AppModule::class, UserModule::class)
class EndToEndTests {
    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navigator = Mockito.mock(DestinationsNavigator::class.java)
            DlightTheme {
                DestinationsNavHost(
                    navGraph = NavGraphs.root
                )
            }
        }
    }

    @Test
    fun get_user_data_followers_followings_repos(){
        composeRule.onNodeWithContentDescription("Default Search Icon").performClick()

        composeRule.onNodeWithTag("SearchAppBar_TextField").performTextInput("JoelKanyi")

            composeRule.onNodeWithText("JoelKanyi").performImeAction()


        runBlocking {
            //composeRule.onNodeWithTag("Name").assertIsDisplayed()
            composeRule.onNodeWithText("Joel Kanyi").assertIsDisplayed()
        }
        //composeRule.onNodeWithText("JoelKanyi").assertIsDisplayed()

        //composeRule.onNodeWithText("Followers").performClick()
    }
}