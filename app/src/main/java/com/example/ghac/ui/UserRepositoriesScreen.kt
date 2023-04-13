package com.example.ghac.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.ghac.domain.model.GithubRepository

@Composable
fun UserRepositoriesScreen(username: String, viewModel: UserRepositoriesViewModel) {

    val lazyPagingItems: LazyPagingItems<GithubRepository> =
        viewModel.pagingFlow(username).collectAsLazyPagingItems()

    Column {
        Text(text = "username = $username")
    }

    LazyColumn {
        items(items = lazyPagingItems) { item ->
            Row {
                Text(item?.name ?: "no name")
            }
        }
    }
}

// @Composable
// fun SelectQuantityButton(
//    @StringRes labelResourceId: Int,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier
// ) {
//    Button(
//        onClick = onClick,
//        modifier = modifier.widthIn(min = 250.dp)
//    ) {
//        Text(stringResource(labelResourceId))
//    }
// }
