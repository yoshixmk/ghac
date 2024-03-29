package com.example.ghac.domain.model

// likes Response Repo
class GithubRepo(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String?,
)
