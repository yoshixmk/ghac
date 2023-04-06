package com.example.ghac.domain.repository

import com.example.ghac.domain.model.GithubRepository

typealias GithubRepositories = List<GithubRepository>

interface GithubRepositoryRepository {
    suspend fun getGithubRepositoriesById(id: Long): GithubRepositories
}
