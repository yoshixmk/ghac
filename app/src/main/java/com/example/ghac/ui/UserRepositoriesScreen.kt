package com.example.ghac.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.ghac.domain.model.GithubRepository

@Composable
fun UserRepositoriesScreen(
    username: String,
    viewModel: UserRepositoriesViewModel = hiltViewModel()
) {

    val lazyPagingItems: LazyPagingItems<GithubRepository> =
        viewModel.pagingFlow(username).collectAsLazyPagingItems()

    Column {
        Text(text = "username = $username") // TODO create top panel
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = lazyPagingItems) { item ->
                item?.let {
                    ElevatedCard {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(item.name)
                            item.description?.let { Text(it) }
                            Row(modifier = Modifier.align(alignment = Alignment.End)) {
                                item.language?.let {
                                    Text(it)
                                    Spacer(modifier = Modifier.size(8.dp))
                                }
                                Icon(
                                    painter = rememberVectorPainter(image = Icons.Default.Star),
                                    contentDescription = "star icon",
                                )
                                Text(item.stars.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}
