package com.example.ghac.domain.repository

import com.example.ghac.domain.model.GithubRepository

typealias GithubUsers = List<GithubRepository>

interface GithubUserRepository {
    interface RepositoryRepository {
        suspend fun getGithubRepositoriesByByUsername(username: String): GithubUsers
    }
}
