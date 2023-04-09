package com.example.ghac.data.repository

import com.example.ghac.api.GithubService
import com.example.ghac.domain.model.GithubUser
import com.example.ghac.domain.repository.GithubUserRepository
import com.example.ghac.domain.repository.GithubUsers
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(
    private val githubService: GithubService,
) : GithubUserRepository {

    override suspend fun getGithubUsersByByKeyword(
        keyword: String,
        position: Int,
        pagingSize: Int
    ): GithubUsers {
        if (keyword.isBlank()) return emptyList()

        val searchedUsers = githubService.searchUsers(
            query = keyword,
            sort = "followers",
            order = "desc",
            page = position,
            itemsPerPage = pagingSize,
        )
        return searchedUsers.items.map {
            GithubUser(
                id = it.id,
                name = it.login,
                avatar_url = it.avatar_url,
            )
        }
    }
}