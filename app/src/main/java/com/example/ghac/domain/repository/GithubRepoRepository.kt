package com.example.ghac.domain.repository

import com.example.ghac.domain.model.GithubRepo

interface GithubRepoRepository {
    suspend fun getGithubRepositoriesByUsername(
        username: String,
        page: Int,
        itemsPerPage: Int
    ): List<GithubRepo>
}
