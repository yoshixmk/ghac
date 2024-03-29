package com.example.ghac.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.example.ghac.R
import com.example.ghac.domain.model.GithubUserItem

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun UserSearchScreen(
    onNext: (username: String) -> Unit = {},
    viewModel: UserSearchViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf("") }
    val lazyPagingItems: LazyPagingItems<GithubUserItem> =
        viewModel.pagingFlow.collectAsLazyPagingItems()

    Column {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(R.string.user_name)) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier.clickable { text = "" },
                    )
                },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .clickable {
                        viewModel.setQuery(keyword = text)
                        lazyPagingItems.refresh()
                    }
                    .fillMaxWidth()
            )
        }
        GithubUserPagingList(onNext, lazyPagingItems)

        if (viewModel.uiState.value.keyword.isNotEmpty() && lazyPagingItems.itemCount == 0) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(stringResource(R.string.result_not_exist_user))
            }
        }
    }
}

@Composable
fun GithubUserPagingList(
    onNext: (username: String) -> Unit = {}, lazyPagingItems: LazyPagingItems<GithubUserItem>
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey(),
            contentType = lazyPagingItems.itemContentType()
        ) { index ->
            val item = lazyPagingItems[index]
            Row {
                AsyncImage(
                    model = item?.avatar_url,
                    placeholder = painterResource(R.drawable.ic_android_56dp),
                    error = painterResource(R.drawable.ic_android_56dp),
                    contentDescription = "user icon",
                    modifier = Modifier
                        .width(56.dp)
                        .clip(CircleShape)
                )
                TextButton(onClick = { onNext(item?.name ?: "yoshixmk") }) {
                    Text(item?.name ?: "no name")
                }
            }
        }
    }
}
