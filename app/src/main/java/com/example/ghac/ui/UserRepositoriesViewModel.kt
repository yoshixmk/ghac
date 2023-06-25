package com.example.ghac.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ghac.domain.model.GithubRepo
import com.example.ghac.domain.pagingsource.GithubRepositoriesPagingSource
import com.example.ghac.domain.repository.GithubRepositoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class UserRepositoriesViewModel @Inject constructor(
    private val githubRepositoryRepository: GithubRepositoryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserSearchViewModel.UiState())
    val uiState: StateFlow<UserSearchViewModel.UiState> = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun pagingFlow(username: String): Flow<PagingData<GithubRepo>> {
        return _uiState.filterNotNull().flatMapLatest {
            Pager(PagingConfig(pageSize = 20)) {
                GithubRepositoriesPagingSource(githubRepositoryRepository, username)
            }.flow.cachedIn(viewModelScope)
        }
    }
}
