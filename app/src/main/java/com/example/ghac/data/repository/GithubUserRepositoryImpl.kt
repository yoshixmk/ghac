package com.example.ghac.data.repository

import com.example.ghac.domain.repository.GithubUserRepository
import com.example.ghac.domain.repository.GithubUsers
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor() : GithubUserRepository {
    override suspend fun getGithubUsersByByKeyword(
        keyword: String,
        position: Int,
        paging_size: Int
    ): GithubUsers {
        TODO("Not yet implemented")
    }
}