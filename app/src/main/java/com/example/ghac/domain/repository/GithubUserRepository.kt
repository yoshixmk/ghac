package com.example.ghac.domain.repository

import com.example.ghac.domain.model.GithubUser

typealias GithubUsers = List<GithubUser>

interface GithubUserRepository {
    suspend fun getGithubUsersByByKeyword(
        keyword: String,
        position: Int = 1,
        paging_size: Int = 20
    ): GithubUsers
}
