package com.example.ghac.data.repository

import com.example.ghac.domain.model.GithubUser
import com.example.ghac.domain.repository.GithubUserRepository
import com.example.ghac.domain.repository.GithubUsers
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor() : GithubUserRepository {
    private var position = 0

    override suspend fun getGithubUsersByByKeyword(
        keyword: String,
        position: Int,
        paging_size: Int
    ): GithubUsers {
        // TODO
        if (position > 10) return emptyList()
        this.position = position + 1
        return listOf(
            GithubUser(
                login = "login",
                id = this.position.toLong(),
                name = "name",
                avatar_url = "https://avatar_url"
            )
        )
    }
}