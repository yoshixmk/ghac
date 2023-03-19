package com.example.ghac.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ghac.R

@Composable
fun UserSearchScreen(
    onNextButtonClicked: () -> Unit = {},
) {
    var text by remember { mutableStateOf("") }

    Column {
        Row {
            TextField(value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(R.string.user_name)) })
            Button(onClick = onNextButtonClicked) {
                Text(stringResource(R.string.user_search))
            }
        }
        Image(
            painter = painterResource(R.drawable.ic_android_56dp), contentDescription = "ドロイドアイコン"
        )
    }
}

//@Composable
//fun SelectQuantityButton(
//    @StringRes labelResourceId: Int,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Button(
//        onClick = onClick,
//        modifier = modifier.widthIn(min = 250.dp)
//    ) {
//        Text(stringResource(labelResourceId))
//    }
//}
