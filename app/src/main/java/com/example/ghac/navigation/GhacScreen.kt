package com.example.ghac.navigation

import androidx.annotation.StringRes
import com.example.ghac.R

enum class GhacScreen(@StringRes val title: Int) {
    UserSearch(title = R.string.user_search),
    UserRepositories(title = R.string.user_repository)
}
