package com.example.ghac.domain.repository

import com.example.ghac.domain.model.GithubUser
import com.example.ghac.domain.model.GithubUserItem

typealias GithubUsers = List<GithubUserItem>

interface GithubUserRepository {
    suspend fun getGithubUser(username: String): GithubUser

    suspend fun getGithubUsersByKeyword(
        keyword: String,
        position: Int = 1,
        pagingSize: Int = 20
    ): GithubUsers
}
