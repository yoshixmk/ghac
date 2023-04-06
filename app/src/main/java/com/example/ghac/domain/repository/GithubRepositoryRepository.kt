package com.example.ghac.domain.repository

import com.example.ghac.domain.model.GithubRepository

typealias GithubRepositories = List<GithubRepository>

interface GithubRepositoryRepository {
    interface RepositoryRepository {
//        fun getGithubRepositoriesFromRoom(): Flow<GithubRepositories>
//        suspend fun getRepositoryFromRoom(id: Int): GithubRepository

        suspend fun getGithubRepositoriesById(id: Long): GithubRepository
    }
}
