package com.example.ghac.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ghac.domain.repository.GithubUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val userRepository: GithubUserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    val pagingFlow = _uiState.filterNotNull().flatMapLatest {
        Pager(PagingConfig(pageSize = 20)) {
            UserSearchPagingSource(userRepository, it.keyword)
        }.flow.cachedIn(viewModelScope)
    }

    fun setQuery(keyword: String) {
        _uiState.value = _uiState.value.copy(
            keyword = keyword
        )
    }
}

data class UiState(
    val keyword: String = ""
)
