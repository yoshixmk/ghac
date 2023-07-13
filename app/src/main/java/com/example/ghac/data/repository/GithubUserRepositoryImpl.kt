package com.example.ghac.data.repository

import com.example.ghac.api.GithubService
import com.example.ghac.domain.model.GithubUser
import com.example.ghac.domain.model.GithubUserItem
import com.example.ghac.domain.repository.GithubUserRepository
import com.example.ghac.domain.repository.GithubUsers
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(
    private val githubService: GithubService,
) : GithubUserRepository {

    override suspend fun getGithubUser(
        username: String,
    ): GithubUser {
        val user = githubService.getUser(username)
        return GithubUser(
            user.id,
            user.login,
            user.node_id,
            user.avatar_url,
            user.gravatar_id,
            user.url,
            user.html_url,
            user.followers_url,
            user.following_url,
            user.gists_url,
            user.starred_url,
            user.subscriptions_url,
            user.organizations_url,
            user.repos_url,
            user.received_events_url,
            user.events_url,
            user.type,
            user.name,
            user.company,
            user.email,
            user.followers,
            user.following,
        )
    }

    override suspend fun getGithubUsersByKeyword(
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
            GithubUserItem(
                id = it.id,
                name = it.login,
                avatar_url = it.avatar_url,
            )
        }
    }
}