package com.example.ghac.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ghac.ui.UserRepositoriesScreen
import com.example.ghac.ui.UserSearchScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = GhacScreen.UserSearch.name,
        modifier = modifier
    ) {
        composable(route = GhacScreen.UserSearch.name) {
            UserSearchScreen(onNextButtonClicked = { navController.navigate(GhacScreen.UserRepositories.name) })
        }
        composable(route = GhacScreen.UserRepositories.name) {
            UserRepositoriesScreen()
        }
    }
}
