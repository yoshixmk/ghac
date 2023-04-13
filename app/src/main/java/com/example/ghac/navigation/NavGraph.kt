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
                onNext = { id -> navController.navigate(GhacScreen.UserRepositories.name + "/$id") },
                viewModel = viewModel
            )
        }
        composable(
            route = GhacScreen.UserRepositories.name + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0L
            UserRepositoriesScreen(id)
        }
    }
}
