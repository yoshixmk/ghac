package com.example.ghac.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ghac.ui.UserReposScreen
import com.example.ghac.ui.UserSearchScreen

@Composable
fun NavGraph(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = GhacScreen.UserSearch.name,
        modifier = modifier
    ) {
        composable(route = GhacScreen.UserSearch.name) {
            UserSearchScreen(
                onNext = { username -> navController.navigate(GhacScreen.UserRepositories.name + "/$username") }
            )
        }
        composable(
            route = GhacScreen.UserRepositories.name + "/{username}",
            arguments = listOf(navArgument("username") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            UserReposScreen(username)
        }
    }
}
