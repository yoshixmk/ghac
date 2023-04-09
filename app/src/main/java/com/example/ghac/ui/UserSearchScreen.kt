package com.example.ghac.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.ghac.R
import com.example.ghac.domain.model.GithubUser

@Composable
fun UserSearchScreen(
    onNext: (id: Long) -> Unit = {},
    viewModel: UserSearchViewModel
) {
    var text by remember { mutableStateOf("") }
    val lazyPagingItems: LazyPagingItems<GithubUser> =
        viewModel.pagingFlow.collectAsLazyPagingItems()

    Column {
        Row {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(R.string.user_name)) }
            )
            Button(onClick = {
                viewModel.setQuery(keyword = text)
                lazyPagingItems.refresh()
            }) {
                Text(stringResource(R.string.user_search))
            }
        }
        GithubUserPagingList(onNext, lazyPagingItems)
    }
}

@Composable
fun GithubUserPagingList(
    onNext: (id: Long) -> Unit = {},
    lazyPagingItems: LazyPagingItems<GithubUser>
) {
    LazyColumn {
        items(items = lazyPagingItems) { item ->
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_android_56dp),
                    contentDescription = "ドロイドアイコン"
                )
                TextButton(onClick = { onNext(item?.id ?: 0L) }) {
                    Text(item?.name ?: "no name")
                }
            }
        }
    }
}
