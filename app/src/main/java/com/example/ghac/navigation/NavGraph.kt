package com.example.ghac.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ghac.ui.UserRepositoriesScreen
import com.example.ghac.ui.UserRepositoriesViewModel
import com.example.ghac.ui.UserSearchScreen
import com.example.ghac.ui.UserSearchViewModel

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
            val viewModel: UserSearchViewModel = hiltViewModel()
            UserSearchScreen(
                onNext = { username -> navController.navigate(GhacScreen.UserRepositories.name + "/$username") },
                viewModel = viewModel
            )
        }
        composable(
            route = GhacScreen.UserRepositories.name + "/{username}",
            arguments = listOf(navArgument("username") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val viewModel: UserRepositoriesViewModel = hiltViewModel()
            val username = backStackEntry.arguments?.getString("username") ?: ""
            UserRepositoriesScreen(username, viewModel)
        }
    }
}
