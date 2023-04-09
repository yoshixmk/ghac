package com.example.ghac.api

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
    @SerializedName("total_count") val total_count: Int = 0,
    @SerializedName("incomplete_results") val incomplete_results: Boolean = false,
    @SerializedName("items") val items: List<User> = emptyList(),
    val nextPage: Int? = null
)

data class User(
    @field:SerializedName("login") val login: String,
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("avatar_url") val avatar_url: String
)

data class Repo(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("full_name") val fullName: String,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("html_url") val url: String,
    @field:SerializedName("stargazers_count") val stars: Int,
    @field:SerializedName("forks_count") val forks: Int,
    @field:SerializedName("language") val language: String?,
    @field:SerializedName("owner") val owner: Owner
)

data class Owner(
    @field:SerializedName("login") val login: String,
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("avatar_url") val avatar_url: String
)
