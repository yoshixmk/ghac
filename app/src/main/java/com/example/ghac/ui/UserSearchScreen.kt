package com.example.ghac.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.ghac.R
import com.example.ghac.domain.model.GithubUser

@Composable
fun UserSearchScreen(
    onNext: (username: String) -> Unit = {},
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
    onNext: (username: String) -> Unit = {},
    lazyPagingItems: LazyPagingItems<GithubUser>
) {
    LazyColumn {
        items(items = lazyPagingItems) { item ->
            Row {
                AsyncImage(
                    model = item?.avatar_url,
                    placeholder = painterResource(R.drawable.ic_android_56dp),
                    error = painterResource(R.drawable.ic_android_56dp),
                    contentDescription = "user icon",
                    modifier = Modifier.width(56.dp)
                )
                TextButton(onClick = { onNext(item?.name ?: "yoshixmk") }) {
                    Text(item?.name ?: "no name")
                }
            }
        }
    }
}
