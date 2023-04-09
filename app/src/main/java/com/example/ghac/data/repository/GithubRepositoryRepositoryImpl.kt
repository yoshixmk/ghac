package com.example.ghac.data.repository

import com.example.ghac.domain.repository.GithubRepositories
import com.example.ghac.domain.repository.GithubRepositoryRepository
import javax.inject.Inject

class GithubRepositoryRepositoryImpl @Inject constructor() : GithubRepositoryRepository {
    override suspend fun getGithubRepositoriesById(id: Long): GithubRepositories {
        TODO("Not yet implemented")
    }
}