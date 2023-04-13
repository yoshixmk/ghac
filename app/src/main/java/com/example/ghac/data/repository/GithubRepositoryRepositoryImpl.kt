package com.example.ghac.data.repository

import com.example.ghac.api.GithubService
import com.example.ghac.domain.model.GithubRepository
import com.example.ghac.domain.repository.GithubRepositories
import com.example.ghac.domain.repository.GithubRepositoryRepository
import javax.inject.Inject

class GithubRepositoryRepositoryImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRepositoryRepository {
    override suspend fun getGithubRepositoriesByUsername(username: String): GithubRepositories {
        return githubService.getRepos(
            username = username,
            sort = null,
            direction = null,
            page = null,
            itemsPerPage = null
        ).map {
            GithubRepository(
                id = it.id,
                name = it.name,
                fullName = it.fullName,
                description = it.description,
                url = it.url,
                stars = it.stars,
                forks = it.forks,
                language = it.language,
            )
        }
    }
}