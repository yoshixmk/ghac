package com.example.ghac.domain.repository

import com.example.ghac.domain.model.GithubRepo

typealias GithubRepositories = List<GithubRepo>

interface GithubRepositoryRepository {
    suspend fun getGithubRepositoriesByUsername(
        username: String,
        page: Int,
        itemsPerPage: Int
    ): GithubRepositories
}
