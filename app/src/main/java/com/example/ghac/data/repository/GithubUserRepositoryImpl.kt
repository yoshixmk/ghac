package com.example.ghac.data.repository

import com.example.ghac.domain.model.GithubUser
import com.example.ghac.domain.repository.GithubUserRepository
import com.example.ghac.domain.repository.GithubUsers
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor() : GithubUserRepository {
    override suspend fun getGithubUsersByByKeyword(
        keyword: String,
        position: Int,
        paging_size: Int
    ): GithubUsers {
        // TODO
        return listOf(
            GithubUser(
                login = "login",
                id = 1,
                name = "name",
                avatar_url = "https://avatar_url"
            )
        )
    }
}