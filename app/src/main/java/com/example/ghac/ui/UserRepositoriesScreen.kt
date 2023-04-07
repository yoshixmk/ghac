package com.example.ghac.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.ghac.R

@Composable
fun UserRepositoriesScreen(id: Long) {
    Column {
        Text(text = "id = $id")
        Text(stringResource(R.string.user_repository))
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
