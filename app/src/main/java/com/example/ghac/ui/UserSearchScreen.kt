package com.example.ghac.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.ghac.R

@Composable
fun UserSearchScreen(
    onNextButtonClicked: () -> Unit = {},
    userSearchViewModel: UserSearchViewModel
) {
    var text by remember { mutableStateOf("") }

    Column {
        Row {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(R.string.user_name)) }
            )
            Button(onClick = onNextButtonClicked) {
                Text(stringResource(R.string.user_search))
            }
        }
        Image(
            painter = painterResource(R.drawable.ic_android_56dp),
            contentDescription = "ドロイドアイコン"
        )
        GithubUserPagingList(userSearchViewModel)
    }
}

@Composable
fun GithubUserPagingList(
    viewModel: UserSearchViewModel
) {
    val lazyPagingItems = viewModel.pagingFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(items = lazyPagingItems) { item ->
            Text(text = item?.name ?: "no name")
        }
    }
}
