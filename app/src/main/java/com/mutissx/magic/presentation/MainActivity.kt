package com.mutissx.magic.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mutissx.magic.R
import com.mutissx.magic.presentation.common.components.TopBar
import com.mutissx.magic.presentation.screens.Screen
import com.mutissx.magic.presentation.screens.candidate_info.CandidateInfoScreen
import com.mutissx.magic.presentation.screens.cards_detail.CardsDetailScreen
import com.mutissx.magic.presentation.screens.cards_list.CardsListScreen
import com.mutissx.magic.presentation.ui.theme.MagicTheme
import com.mutissx.magic.presentation.util.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MagicTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                var nameScreen by rememberSaveable() {
                    mutableStateOf("")
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopBar(
                                navController = navController,
                                nameScreen = nameScreen
                            )
                        }, content = { paddingValues ->
                            Box(Modifier.padding(paddingValues)) {
                                NavHost(
                                    navController = navController,
                                    startDestination = Screen.CandidateInfoScreen.route
                                ) {
                                    composable(Screen.CandidateInfoScreen.route) {
                                        nameScreen =
                                            stringResource(id = R.string.title_candidate_info)
                                        CandidateInfoScreen(navController = navController)
                                    }
                                    composable(
                                        Screen.CardsListScreen.route
                                    ) {
                                        nameScreen = stringResource(id = R.string.title_cards_list)
                                        CardsListScreen(navController = navController)
                                    }
                                    composable(
                                        Screen.CardsDetailScreen.route,
                                        arguments = listOf(navArgument(Constants.CARDS_DETAILS_ARGUMENT_KEY) {
                                            type = NavType.StringType
                                        })
                                    ) {
                                        nameScreen =
                                            stringResource(id = R.string.title_cards_detail)
                                        CardsDetailScreen(navController = navController)
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
