package com.example.ghac.data.repository

import com.example.ghac.api.GithubService
import com.example.ghac.domain.model.GithubRepo
import com.example.ghac.domain.repository.GithubRepoRepository
import javax.inject.Inject

class GithubRepoRepositoryImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRepoRepository {
    override suspend fun getGithubRepositoriesByUsername(
        username: String,
        page: Int,
        itemsPerPage: Int
    ): List<GithubRepo> {
        return githubService.getRepos(
            username = username,
            sort = "updated",
            direction = "desc",
            page = page,
            itemsPerPage = itemsPerPage
        ).map {
            GithubRepo(
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
