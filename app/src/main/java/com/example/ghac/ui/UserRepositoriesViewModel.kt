package com.example.ghac.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ghac.domain.model.GithubRepo
import com.example.ghac.domain.model.GithubUser
import com.example.ghac.domain.pagingsource.GithubRepositoriesPagingSource
import com.example.ghac.domain.repository.GithubRepoRepository
import com.example.ghac.domain.repository.GithubUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepositoriesViewModel @Inject constructor(
    private val githubRepoRepository: GithubRepoRepository,
    private val githubUserRepository: GithubUserRepository,
) : ViewModel() {
    private val _uiStatePre = MutableStateFlow(UserSearchViewModel.UiState())
    val uiStatePre: StateFlow<UserSearchViewModel.UiState> = _uiStatePre.asStateFlow()

    data class UiState(
        val user: GithubUser? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    // I think there is the better idea
    fun refreshUser(username: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    user = githubUserRepository.getGithubUser(username)
                )
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun pagingFlow(username: String): Flow<PagingData<GithubRepo>> {
        return _uiStatePre.filterNotNull().flatMapLatest {
            Pager(PagingConfig(pageSize = 20)) {
                GithubRepositoriesPagingSource(githubRepoRepository, username)
            }.flow.cachedIn(viewModelScope)
        }
    }
}
