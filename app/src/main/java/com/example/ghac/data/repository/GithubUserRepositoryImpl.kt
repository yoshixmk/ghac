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
        val searchedUsers = githubService.searchUsers(
            query = keyword,
            sort = "followers",
            order = "desc",
            page = position,
            itemsPerPage = pagingSize,
        )
        return searchedUsers.items.map {
            GithubUser(
                login = it.login,
                id = it.id,
                name = it.name,
                avatar_url = it.avatar_url,
            )
        }
    }
}