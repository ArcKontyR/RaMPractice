package ru.arckontyr.rampractice.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import ru.arckontyr.rampractice.ui.character.CharacterScreen
import ru.arckontyr.rampractice.ui.list.CharacterListScreen
import ru.arckontyr.rampractice.ui.navigation.enums.AppDestinations

@PreviewScreenSizes
@Composable
fun RaMPracticeApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.CHARACTERS) }
    val navController = rememberNavController()
    val currentRoute by navController.currentBackStackEntryAsState()
    val route = currentRoute?.destination?.route

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            painterResource(destination.icon),
                            contentDescription = destination.label
                        )
                    },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = { currentDestination = destination
                        if (route != destination.route) {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = false
                            }
                        }}
                )
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AppDestinations.CHARACTERS.route,
        ) {
            composable(AppDestinations.CHARACTERS.route) {
                CharacterListScreen(onCharacterClick = { id ->
                    navController.navigate(Routes.getCharacterByID(id))
                })
            }

            composable(AppDestinations.FAVORITES.route) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = "Hi, you on FAVOURITES",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

            composable(AppDestinations.PROFILE.route) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = "Hi, you on PROFILE",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

            composable(
                route = Routes.CHARACTER,
                arguments = listOf(
                    navArgument(Routes.ID) { type = NavType.IntType }
                )
            ) {
                CharacterScreen(onBack = {
                    navController.popBackStack()
                })
            }
        }
    }
}