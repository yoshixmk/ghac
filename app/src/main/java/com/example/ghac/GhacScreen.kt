package com.example.ghac

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ghac.ui.UserRepositoriesScreen
import com.example.ghac.ui.UserSearchScreen

enum class GhacScreen(@StringRes val title: Int) {
    UserSearch(title = R.string.user_search),
    UserRepositories(title = R.string.user_repository),
}

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
//    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = GhacScreen.valueOf(
        backStackEntry?.destination?.route ?: GhacScreen.UserSearch.name
    )

    Scaffold(
        topBar = {
            GhacAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
//        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = GhacScreen.UserSearch.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = GhacScreen.UserSearch.name) {
                UserSearchScreen(onNextButtonClicked = { navController.navigate(GhacScreen.UserRepositories.name) })
            }
            composable(route = GhacScreen.UserRepositories.name) {
                UserRepositoriesScreen()
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    GhacTheme {
//        Greeting2("Android")
//    }
//}