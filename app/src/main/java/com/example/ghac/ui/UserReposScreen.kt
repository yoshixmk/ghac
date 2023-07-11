package com.example.ghac.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PeopleOutline
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.ghac.domain.model.GithubRepo
import com.example.ghac.ui.theme.BlackThin

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun UserReposScreen(
    username: String,
    viewModel: UserRepoViewModel = hiltViewModel(),
) {

    val lazyPagingItems: LazyPagingItems<GithubRepo> =
        viewModel.pagingFlow(username).collectAsLazyPagingItems()

    val uiState: State<UserRepoViewModel.UiState> = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        if (uiState.value is UserRepoViewModel.UiState.Success) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = (uiState.value as UserRepoViewModel.UiState.Success).user.avatar_url,
                    placeholder = painterResource(R.drawable.ic_android_56dp),
                    error = painterResource(R.drawable.ic_android_56dp),
                    contentDescription = "user icon",
                    modifier = Modifier
                        .width(56.dp)
                        .clip(CircleShape)
                )
                Column {
                    (uiState.value as UserRepoViewModel.UiState.Success).user.name?.let { Text(text = it) }
                    Text(text = username, color = Color.Gray)
                }
            }
            Row(
                modifier = Modifier.align(alignment = Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(imageVector = Icons.Default.PeopleOutline, contentDescription = "people")
                Text(text = "${(uiState.value as UserRepoViewModel.UiState.Success).user.followers} followers")
                Text(text = "・")
                Text(text = "${(uiState.value as UserRepoViewModel.UiState.Success).user.following} following")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))

        UserRepoPagingList(lazyPagingItems)

        if (viewModel.uiStatePre.value.keyword.isNotEmpty() && lazyPagingItems.itemCount == 0) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(stringResource(R.string.result_not_exist_repository))
            }
        }
    }
}

@Composable
fun UserRepoPagingList(lazyPagingItems: LazyPagingItems<GithubRepo>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey(),
            contentType = lazyPagingItems.itemContentType()
        ) { index ->
            val item = lazyPagingItems[index]
            item?.let {
                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        item.description?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium,
                                color = BlackThin
                            )
                        }
                        Row(modifier = Modifier.align(alignment = Alignment.End)) {
                            item.language?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                            }
                            Icon(
                                imageVector = Icons.Default.StarOutline,
                                contentDescription = "star icon",
                                modifier = Modifier.paddingFromBaseline(bottom = 8.dp)
                            )
                            Text(item.stars.toString())
                        }
                    }
                }
            }
        }
    }
}
