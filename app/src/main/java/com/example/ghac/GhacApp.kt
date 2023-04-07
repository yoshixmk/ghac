package com.example.ghac

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ghac.navigation.GhacScreen
import com.example.ghac.navigation.NavGraph

@Composable
fun GhacAppBar(
    currentScreen: GhacScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun GhacApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val route = fun(): String {
        val r = backStackEntry?.destination?.route ?: return GhacScreen.UserSearch.name
        val regex = Regex("""^(\w+)/?.*""")
        return regex.find(r)?.groupValues?.get(1) ?: GhacScreen.UserSearch.name
    }()
    val currentScreen = GhacScreen.valueOf(route)

    Scaffold(
        topBar = {
            GhacAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            modifier = modifier.padding(innerPadding)
        )
    }
}
